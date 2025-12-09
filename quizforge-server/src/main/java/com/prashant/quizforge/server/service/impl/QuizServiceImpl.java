package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.dto.QuizDTO;
import com.prashant.quizforge.server.dto.StudentAnswerDTO;
import com.prashant.quizforge.server.dto.StudentQuizAttemptDTO;
import com.prashant.quizforge.server.entity.*;
import com.prashant.quizforge.server.exception.DuplicateActionException;
import com.prashant.quizforge.server.exception.QuizTimeOverException;
import com.prashant.quizforge.server.exception.ResourceNotFoundException;
import com.prashant.quizforge.server.repositoriy.QuestionRepository;
import com.prashant.quizforge.server.repositoriy.QuizRepository;
import com.prashant.quizforge.server.repositoriy.StudentAnswerRepository;
import com.prashant.quizforge.server.repositoriy.StudentQuizAttemptRepository;
import com.prashant.quizforge.server.service.QuizService;
import com.prashant.quizforge.server.service.UserService;
import com.prashant.quizforge.server.utils.RandomStringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;
    private final UserService userService;
    private final QuestionRepository questionRepository;
    private final StudentAnswerRepository studentAnswerRepository;
    private final StudentQuizAttemptRepository quizAttemptRepository;

    @Override
    @Transactional
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        log.info("Request received to create quiz with title='{}'", quizDTO.getTitle());

        Quiz quiz = convertToEntity(quizDTO);

        String link;
        do {
            link = RandomStringUtil.generateSecureRandomString();
        } while (quizRepository.existsByLink(link));

        quiz.setLink(link);

        Quiz savedQuiz = quizRepository.save(quiz);
        log.info("Successfully created quiz with id={}", savedQuiz.getId());

        return convertToDTO(savedQuiz);
    }

    @Override
    public QuizDTO getQuizById(Long quizId) {
        log.info("Request received to fetch quiz with id={}", quizId);
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
        log.info("Successfully fetched quiz with id={}", quizId);
        return convertToDTO(quiz);
    }

    @Override
    @Transactional
    public QuizDTO deleteQuiz(Long quizId) {
        log.info("Request received to delete quiz with id={}", quizId);
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
        quiz.setQuestions(null);
        quizRepository.deleteById(quizId);

        log.info("Successfully deleted quiz with id={}", quizId);

        return convertToDTO(quiz);
    }

    @Override
    @Transactional
    public StudentQuizAttemptDTO startQuiz(Long quizId) {
       User user = userService.getCurrentUser();
       Quiz quiz = quizRepository.findById(quizId).orElseThrow(()->
           new ResourceNotFoundException("Invalid quizId: "+ quizId));

       boolean alreadyStarted = quizAttemptRepository.findByQuizAndUser(quiz, user).isPresent();
       if (alreadyStarted) {
            throw new DuplicateActionException("You have already started this quiz.");
       }

       StudentQuizAttempt sqa = new  StudentQuizAttempt();
       sqa.setQuiz(quiz);
       sqa.setUser(user);
       sqa.setStartedAt(LocalDateTime.now());
       int time = quiz.getTimeLimit();
       sqa.setFinishedAt(LocalDateTime.now().plusMinutes(time));
       sqa.setScore(0);

       quizAttemptRepository.save(sqa);
       return modelMapper.map(sqa,StudentQuizAttemptDTO.class);
    }

    @Override
    @Transactional
    public StudentAnswerDTO submitAnswer(Long quizId, Long questionId, StudentAnswerDTO answerDTO) {

        Question question = questionRepository.findById(questionId).orElseThrow(() ->
                new ResourceNotFoundException("Invalid question id: " + questionId));

        Quiz quiz = quizRepository.findById(quizId).orElseThrow(() ->
                new ResourceNotFoundException("Invalid quiz id: " + quizId));

        User user = userService.getCurrentUser();

        StudentQuizAttempt quizAttempt = quizAttemptRepository
                .findByQuizAndUser(quiz, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz is not started by the user"));

        // Check time limit
        if (LocalDateTime.now().isAfter(quizAttempt.getFinishedAt()))
            throw new QuizTimeOverException("Time is over for this quiz.");

        //  Check duplicate answer
        boolean alreadyAnswered = studentAnswerRepository
                .existsByStudentQuizAttemptAndQuestion(quizAttempt, question);

        if (alreadyAnswered) {
            throw new DuplicateActionException("You have already submitted an answer for this question.");
        }

        // Create and save new answer
        StudentAnswer studentAnswer = new StudentAnswer();
        studentAnswer.setSelectedOption(answerDTO.getSelectedOption());
        studentAnswer.setCorrect(answerDTO.isCorrect());
        studentAnswer.setQuestion(question);
        studentAnswer.setStudentQuizAttempt(quizAttempt);

        StudentAnswer savedAnswer = studentAnswerRepository.save(studentAnswer);
        if (answerDTO.getSelectedOption().equals(question.getCorrectAnswer()))
            quizAttempt.setScore(quizAttempt.getScore()+question.getMarks());
        quizAttemptRepository.save(quizAttempt);
        return modelMapper.map(savedAnswer, StudentAnswerDTO.class);
    }

    private QuizDTO convertToDTO(Quiz quiz) {
        return modelMapper.map(quiz, QuizDTO.class);
    }

    private Quiz convertToEntity(QuizDTO quizDTO) {
        return modelMapper.map(quizDTO, Quiz.class);
    }
}
