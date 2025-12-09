package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.dto.QuestionDTO;
import com.prashant.quizforge.server.dto.QuizDTO;
import com.prashant.quizforge.server.dto.StudentAnswerDTO;
import com.prashant.quizforge.server.dto.StudentQuizAttemptDTO;
import com.prashant.quizforge.server.exception.ResourceNotFoundException;

import java.util.List;

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
     * @return the QuizDTO, throws ResourceNotFoundException if not found
     */
    QuizDTO getQuizById(Long quizId);

    /**
     * Deletes a quiz by its ID, also deleting all associated questions due to cascade settings.
     *
     * @param quizId the ID of the quiz to delete
     * @return the deleted QuizDTO
     * @throws ResourceNotFoundException if the quiz does not exist
     */
    QuizDTO deleteQuiz(Long quizId);

    /**
     * Starts a quiz for the current user.
     * Throws an exception if the user already started this quiz.
     *
     * @param quizId ID of the quiz to start
     * @return DTO of the started quiz attempt
     */
    StudentQuizAttemptDTO startQuiz(Long quizId);

    /**
     * Submits an answer for a specific question in a quiz attempt.
     * Throws DuplicateActionException if the question was already answered.
     * Throws QuizTimeOverException if the quiz time has expired.
     *
     * @param quizId     ID of the quiz
     * @param questionId ID of the question being answered
     * @param answerDTO  DTO containing selected option and correctness
     * @return DTO of the saved student answer
     */
    StudentAnswerDTO submitAnswer(Long quizId, Long questionId, StudentAnswerDTO answerDTO);

    /**
     * Retrieves the total marks scored by the current user for a given quiz.
     *
     * @param quizId ID of the quiz
     * @return total score as integer
     */
    StudentQuizAttemptDTO getMarksForQuiz(Long quizId);

    /**
     * Retrieves all answers submitted by the current user for a given quiz.
     * Each QuestionDTO will include selected option and correctness.
     *
     * @param quizId ID of the quiz
     * @return list of QuestionDTOs representing attempted questions
     */
    List<QuestionDTO> getStudentAnswersForQuiz(Long quizId);
}
