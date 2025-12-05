package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.dto.QuizDTO;
import com.prashant.quizforge.server.service.QuizService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    @Override
    public QuizDTO createQuiz(QuizDTO quizDTO) {
        return null;
    }

    @Override
    public QuizDTO getQuizById(Long quizId) {
        return null;
    }

    @Override
    public QuizDTO deleteQuiz(Long quizId) {
        return null;
    }
}
