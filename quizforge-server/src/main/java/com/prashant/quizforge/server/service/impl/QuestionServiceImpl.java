package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.dto.QuestionDTO;
import com.prashant.quizforge.server.service.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    @Override
    public QuestionDTO createQuestion(Long quizId, QuestionDTO questionDTO) {
        return null;
    }

    @Override
    public QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO) {
        return null;
    }

    @Override
    public QuestionDTO getQuestionById(Long questionId) {
        return null;
    }

    @Override
    public List<QuestionDTO> getQuestionsByQuizId(Long quizId, Integer pageNo) {
        return List.of();
    }

    @Override
    public QuestionDTO deleteQuestion(Long questionId) {
        return null;
    }
}
