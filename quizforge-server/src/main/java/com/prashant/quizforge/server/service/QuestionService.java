package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.dto.QuestionDTO;
import com.prashant.quizforge.server.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service interface for managing Question entities and their association with quizzes.
 */
public interface QuestionService {

    /**
     * Creates a new question and optionally assigns it to a quiz.
     *
     * @param questionDTO DTO containing question details
     * @return the created QuestionDTO with generated ID
     */
    QuestionDTO createQuestion(QuestionDTO questionDTO);

    /**
     * Updates an existing question.
     *
     * @param questionId  the ID of the question to update
     * @param questionDTO DTO containing updated question details
     * @return the updated QuestionDTO
     * @throws ResourceNotFoundException if the question does not exist
     */
    QuestionDTO updateQuestion(Long questionId, QuestionDTO questionDTO);

    /**
     * Retrieves a question by its ID.
     *
     * @param questionId the ID of the question to retrieve
     * @return the QuestionDTO wrapped in Optional, empty if not found
     */
    Optional<QuestionDTO> getQuestionById(Long questionId);

    /**
     * Retrieves all questions for a particular quiz.
     *
     * @param quizId   the ID of the quiz
     * @param pageable pagination and sorting information
     * @return a page of QuestionDTOs
     */
    Page<QuestionDTO> getQuestionsByQuizId(Long quizId, Pageable pageable);

    /**
     * Deletes a question by its ID.
     *
     * @param questionId the ID of the question to delete
     * @throws ResourceNotFoundException if the question does not exist
     */
    void deleteQuestion(Long questionId);

    /**
     * Assigns an existing question to a quiz.
     *
     * @param questionId the ID of the question
     * @param quizId     the ID of the quiz
     * @return the updated QuestionDTO
     * @throws ResourceNotFoundException if the question or quiz does not exist
     */
    QuestionDTO assignQuestionToQuiz(Long questionId, Long quizId);
}
