package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.dto.QuizDTO;
import com.prashant.quizforge.server.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing Quiz entities and their associated Questions.
 */
public interface QuizService {

    /**
     * Creates a new quiz along with its questions.
     *
     * @param quizDTO DTO containing quiz details and questions
     * @return the created QuizDTO with generated ID and timestamps
     */
    QuizDTO createQuiz(QuizDTO quizDTO);

    /**
     * Updates an existing quiz and its questions.
     *
     * @param quizId  the ID of the quiz to update
     * @param quizDTO DTO containing updated quiz details and questions
     * @return the updated QuizDTO if found
     * @throws ResourceNotFoundException if the quiz does not exist
     */
    QuizDTO updateQuiz(Long quizId, QuizDTO quizDTO);

    /**
     * Retrieves a quiz by its ID, including all associated questions.
     *
     * @param quizId the ID of the quiz to retrieve
     * @return the QuizDTO wrapped in Optional, empty if not found
     */
    Optional<QuizDTO> getQuizById(Long quizId);

    /**
     * Retrieves all quizzes, optionally paginated.
     *
     * @param pageable pagination and sorting information
     * @return a page of QuizDTOs
     */
    Page<QuizDTO> getAllQuizzes(Pageable pageable);

    /**
     * Deletes a quiz by its ID, also deleting all associated questions due to cascade settings.
     *
     * @param quizId the ID of the quiz to delete
     * @throws ResourceNotFoundException if the quiz does not exist
     */
    void deleteQuiz(Long quizId);
}
