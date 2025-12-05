package com.prashant.quizforge.server.controller;

import com.prashant.quizforge.server.dto.QuestionDTO;
import com.prashant.quizforge.server.dto.QuizDTO;
import com.prashant.quizforge.server.service.QuestionService;
import com.prashant.quizforge.server.service.QuizService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/teacher")
@RequiredArgsConstructor
@Slf4j
public class TeacherController {

    private final QuizService quizService;
    private final QuestionService questionService;

    // Create new quiz
    @PostMapping("/quizzes")
    public ResponseEntity<QuizDTO> createQuiz(@Valid @RequestBody QuizDTO quizDTO) {
        QuizDTO savedQuiz = quizService.createQuiz(quizDTO);
        return new ResponseEntity<>(savedQuiz, HttpStatus.CREATED);
    }

    // Get quiz by ID
    @GetMapping("/quizzes/{id}")
    public ResponseEntity<QuizDTO> getQuizById(@PathVariable Long id) {
        QuizDTO quiz = quizService.getQuizById(id);
        return ResponseEntity.ok(quiz);
    }

    // Delete quiz by ID
    @DeleteMapping("/quizzes/{id}")
    public ResponseEntity<QuizDTO> deleteQuizById(@PathVariable Long id) {
        QuizDTO deletedQuiz = quizService.deleteQuiz(id);
        return ResponseEntity.ok(deletedQuiz);
    }

    // Create new question under a quiz
    @PostMapping("/quizzes/{quizId}/questions")
    public ResponseEntity<QuestionDTO> createQuestion(@PathVariable Long quizId, @Valid @RequestBody QuestionDTO questionDTO) {

        QuestionDTO savedQuestion = questionService.createQuestion(quizId, questionDTO);
        return new ResponseEntity<>(savedQuestion, HttpStatus.CREATED);
    }

    // Get question by ID
    @GetMapping("/questions/{id}")
    public ResponseEntity<QuestionDTO> getQuestionById(@PathVariable Long id) {
        QuestionDTO question = questionService.getQuestionById(id);
        return ResponseEntity.ok(question);
    }

    // Delete question by ID
    @DeleteMapping("/questions/{id}")
    public ResponseEntity<QuestionDTO> deleteQuestionById(@PathVariable Long id) {
        QuestionDTO deletedQuestion = questionService.deleteQuestion(id);
        return ResponseEntity.ok(deletedQuestion);
    }

    // Update question
    @PutMapping("/questions/{id}")
    public ResponseEntity<QuestionDTO> updateQuestion(@PathVariable Long id, @Valid @RequestBody QuestionDTO questionDTO) {
        QuestionDTO updatedQuestion = questionService.updateQuestion(id, questionDTO);
        return ResponseEntity.ok(updatedQuestion);
    }

    // get questions by their quizId
    @GetMapping("/quizzes/{quizId}/questions")
    public ResponseEntity<Page<QuestionDTO>> getQuestionsByQuizId(@PathVariable Long quizId, @RequestParam(defaultValue = "0") Integer pageNo) {
        Page<QuestionDTO> questions = questionService.getQuestionsByQuizId(quizId, pageNo);
        return ResponseEntity.ok(questions);
    }

}
