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

        Quiz quiz = getQuizById(quizId);

        Question question = convertToEntity(questionDTO);
        question.setQuiz(quiz);
        Question savedQuestion = questionRepository.save(question);
        return convertToDTO(savedQuestion);
    }

    @Override
    public QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO) {
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
        return convertToDTO(savedQuestion);
    }

    @Override
    public QuestionDTO getQuestionById(Long questionId) {
        Question existingQuestion = questionRepository.findById(questionId).orElseThrow(()->
                new ResourceNotFoundException("Invalid question id:"+ questionId));
        return convertToDTO(existingQuestion);
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuizId(Long quizId, Integer pageNo) {
        Quiz quiz = getQuizById(quizId);
        Pageable pageable = PageRequest.of(pageNo, PAGE_SIZE);
        Page<Question> questionPage = questionRepository.findByQuizId(quizId, pageable);
        return questionPage.getContent()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public QuestionDTO deleteQuestion(Long questionId) {

        Question existingQuestion = questionRepository.findById(questionId).orElseThrow(()->
                new ResourceNotFoundException("Invalid question id:"+ questionId));
        questionRepository.delete(existingQuestion);

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
