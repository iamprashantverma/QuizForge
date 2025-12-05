package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.dto.QuizDTO;
import com.prashant.quizforge.server.entity.Quiz;
import com.prashant.quizforge.server.exception.ResourceNotFoundException;
import com.prashant.quizforge.server.repositoriy.QuizRepository;
import com.prashant.quizforge.server.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final ModelMapper modelMapper;
    private final QuizRepository quizRepository;

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        log.info("Request received to create quiz with title='{}'", quizDTO.getTitle());
        Quiz quiz = convertToEntity(quizDTO);
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
    public QuizDTO deleteQuiz(Long quizId) {
        log.info("Request received to delete quiz with id={}", quizId);
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("Quiz not found with id: " + quizId));
        quizRepository.delete(quiz);
        log.info("Successfully deleted quiz with id={}", quizId);
        return convertToDTO(quiz);
    }

    private QuizDTO convertToDTO(Quiz quiz) {
        return modelMapper.map(quiz, QuizDTO.class);
    }

    private Quiz convertToEntity(QuizDTO quizDTO) {
        return modelMapper.map(quizDTO, Quiz.class);
    }
}
