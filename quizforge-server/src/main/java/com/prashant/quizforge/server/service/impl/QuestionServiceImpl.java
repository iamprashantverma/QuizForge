package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.dto.QuestionDTO;
import com.prashant.quizforge.server.entity.Question;
import com.prashant.quizforge.server.entity.Quiz;
import com.prashant.quizforge.server.exception.ResourceNotFoundException;
import com.prashant.quizforge.server.repositoriy.QuestionRepository;
import com.prashant.quizforge.server.repositoriy.QuizRepository;
import com.prashant.quizforge.server.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuizRepository quizRepository;
    private final ModelMapper modelMapper;

    private final static Integer PAGE_SIZE = 10;

    @Override
    public QuestionDTO createQuestion(Long quizId, QuestionDTO questionDTO) {
        log.info("Creating question for quizId={}", quizId);
        Quiz quiz = getQuizById(quizId);

        Question question = convertToEntity(questionDTO);
        question.setQuiz(quiz);
        Question savedQuestion = questionRepository.save(question);
        log.info("Question created successfully with id={} for quizId={}", savedQuestion.getId(), quizId);
        return convertToDTO(savedQuestion);
    }

    @Override
    public QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO) {

        log.info("Updating question with id={}", questionId);
        Question existingQuestion = questionRepository.findById(questionId).orElseThrow(()->
                new ResourceNotFoundException("Invalid question id:"+ questionId));
        existingQuestion.setQuestionText(questionDTO.getQuestionText());
        existingQuestion.setOptionA(questionDTO.getOptionA());
        existingQuestion.setOptionB(questionDTO.getOptionB());
        existingQuestion.setOptionC(questionDTO.getOptionC());
        existingQuestion.setOptionD(questionDTO.getOptionD());
        existingQuestion.setCorrectAnswer(questionDTO.getCorrectAnswer());
        existingQuestion.setExplanation(questionDTO.getExplanation());
        existingQuestion.setMarks(questionDTO.getMarks());
        Question savedQuestion = questionRepository.save(existingQuestion);
        log.info("Question updated successfully with id={}", savedQuestion.getId());
        return convertToDTO(savedQuestion);
    }

    @Override
    public QuestionDTO getQuestionById(Long questionId) {
        log.info("Fetching question with id={}", questionId);
        Question existingQuestion = questionRepository.findById(questionId).orElseThrow(()->{
            log.error("Question not found with id={}", questionId);
            return new ResourceNotFoundException("Invalid question id:" + questionId);
        });
        log.info("Question fetched successfully with id={}", questionId);
        return convertToDTO(existingQuestion);
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuizId(Long quizId, Integer pageNo) {
        log.info("Fetching questions for quizId={} at pageNo={}", quizId, pageNo);
        Quiz quiz = getQuizById(quizId);
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);
        Page<Question> questionPage = questionRepository.findByQuizId(quizId, pageable);
        List<QuestionDTO> questionDTOs = questionPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();
        log.info("Fetched {} questions for quizId={} at pageNo={}", questionDTOs.size(), quizId, pageNo);
        return questionDTOs;
    }

    @Override
    public QuestionDTO deleteQuestion(Long questionId) {
        log.info("Deleting question with id={}", questionId);
        Question existingQuestion = questionRepository.findById(questionId).orElseThrow(()->
                new ResourceNotFoundException("Invalid question id:"+ questionId));
        questionRepository.delete(existingQuestion);
        log.info("Question deleted successfully with id={}", questionId);
        return convertToDTO(existingQuestion);
    }

    private QuestionDTO convertToDTO(Question question) {
        return modelMapper.map(question,QuestionDTO.class);
    }

    private Question convertToEntity(QuestionDTO questionDTO) {
        return modelMapper.map(questionDTO,Question.class);
    }

    private Quiz getQuizById(Long quizId){
        return quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
    }

}
