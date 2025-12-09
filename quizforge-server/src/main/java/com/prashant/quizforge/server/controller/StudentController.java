package com.prashant.quizforge.server.controller;

import com.prashant.quizforge.server.dto.StudentAnswerDTO;
import com.prashant.quizforge.server.dto.StudentQuizAttemptDTO;
import com.prashant.quizforge.server.service.QuizService;
import com.prashant.quizforge.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {
    private final UserService userService;

    private final QuizService quizService;

    @PostMapping("/start/{quizId}")
    public ResponseEntity<StudentQuizAttemptDTO> startQuiz(@PathVariable Long quizId) {
        StudentQuizAttemptDTO attemptDTO = quizService.startQuiz(quizId);
        return ResponseEntity.ok(attemptDTO);
    }

    @PostMapping("/submit/{quizId}/question/{questionId}")
    public ResponseEntity<StudentAnswerDTO> submitAnswer(
            @PathVariable Long quizId,
            @PathVariable Long questionId,
            @RequestBody StudentAnswerDTO answerDTO) {
        StudentAnswerDTO savedAnswer = quizService.submitAnswer(quizId, questionId, answerDTO);
        return ResponseEntity.ok(savedAnswer);
    }




}
