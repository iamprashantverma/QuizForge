package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.dto.QuestionDTO;
import com.prashant.quizforge.server.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;



public interface QuestionService {

    /**
     * Creates a new question and optionally assigns it to a quiz.
     *
     * @param questionDTO DTO containing question details
     * @return the created QuestionDTO with generated ID
     */
    QuestionDTO createQuestion(Long quizId,QuestionDTO questionDTO);

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
    QuestionDTO getQuestionById(Long questionId);

    /**
     * Retrieves all questions for a particular quiz.
     *
     * @param quizId   the ID of the quiz
     * @param pageNo pagination information
     * @return a page of QuestionDTOs
     */
    Page<QuestionDTO> getQuestionsByQuizId(Long quizId, Integer pageNo,Integer pageSize);

    /**
     * Deletes a question by its ID.
     *
     * @param questionId the ID of the question to delete
     * @throws ResourceNotFoundException if the question does not exist
     */
    QuestionDTO deleteQuestion(Long questionId);


}
