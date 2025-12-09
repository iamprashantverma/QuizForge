package com.prashant.quizforge.server.controller;

import com.prashant.quizforge.server.dto.QuestionDTO;
import com.prashant.quizforge.server.dto.StudentAnswerDTO;
import com.prashant.quizforge.server.dto.StudentQuizAttemptDTO;
import com.prashant.quizforge.server.service.QuizService;
import com.prashant.quizforge.server.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final UserService userService;
    private final QuizService quizService;

    /**
     * Start a quiz for the current student.
     */
    @PostMapping("/quizzes/{quizId}/start")
    public ResponseEntity<StudentQuizAttemptDTO> startQuiz(@PathVariable Long quizId) {
        log.debug("User {} starting quiz {}", userService.getCurrentUser().getEmail(), quizId);
        StudentQuizAttemptDTO attemptDTO = quizService.startQuiz(quizId);
        return ResponseEntity.ok(attemptDTO);
    }

    /**
     * Submit an answer for a specific question in a quiz.
     */
    @PostMapping("/quizzes/{quizId}/questions/{questionId}/submit")
    public ResponseEntity<StudentAnswerDTO> submitAnswer(@PathVariable Long quizId, @PathVariable Long questionId, @Valid @RequestBody StudentAnswerDTO answerDTO) {

        log.debug("User {} submitting answer for quiz {} question {}", userService.getCurrentUser().getEmail(), quizId, questionId);

        StudentAnswerDTO savedAnswer = quizService.submitAnswer(quizId, questionId, answerDTO);
        return ResponseEntity.ok(savedAnswer);
    }

    /**
     * Get total marks for a quiz attempt by the current student.
     */
    @GetMapping("/quizzes/{quizId}/marks")
    public ResponseEntity<StudentQuizAttemptDTO> getQuizMarks(@PathVariable Long quizId) {
        log.debug("User {} fetching marks for quiz {}", userService.getCurrentUser().getEmail(), quizId);
        StudentQuizAttemptDTO studentQuizAttemptDTO = quizService.getMarksForQuiz(quizId);
        return ResponseEntity.ok(studentQuizAttemptDTO);
    }

    /**
     * Get all attempted questions and answers for a quiz by the current student.
     */
    @GetMapping("/quizzes/{quizId}/answers")
    public ResponseEntity<List<QuestionDTO>> getQuizAnswers(@PathVariable Long quizId) {
        log.debug("User {} fetching answers for quiz {}", userService.getCurrentUser().getEmail(), quizId);
        List<QuestionDTO> answers = quizService.getStudentAnswersForQuiz(quizId);
        return ResponseEntity.ok(answers);
    }
}
