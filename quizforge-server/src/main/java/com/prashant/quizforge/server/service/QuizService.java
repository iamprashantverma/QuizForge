package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.dto.QuizDTO;
import com.prashant.quizforge.server.dto.StudentAnswerDTO;
import com.prashant.quizforge.server.dto.StudentQuizAttemptDTO;
import com.prashant.quizforge.server.exception.ResourceNotFoundException;

public interface QuizService {

    /**
     * Creates a new quiz along with its questions.
     *
     * @param quizDTO DTO containing quiz details and questions
     * @return the created QuizDTO with generated ID and timestamps
     */
    QuizDTO createQuiz(QuizDTO quizDTO);

    /**
     * Retrieves a quiz by its ID, including all associated questions.
     *
     * @param quizId the ID of the quiz to retrieve
     * @return the QuizDTO wrapped in Optional, empty if not found
     */
    QuizDTO getQuizById(Long quizId);

    /**
     * Deletes a quiz by its ID, also deleting all associated questions due to cascade settings.
     *
     * @param quizId the ID of the quiz to delete
     * @throws ResourceNotFoundException if the quiz does not exist
     */
    QuizDTO deleteQuiz(Long quizId);

    StudentQuizAttemptDTO startQuiz(Long quizId);

    StudentAnswerDTO submitAnswer(Long quizId, Long questionId, StudentAnswerDTO answerDTO);
}
