package com.prashant.quizforge.server.dto;

import com.prashant.quizforge.server.entity.enums.Difficulty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class QuizDTO {

    private Long id;

    @NotBlank(message = "Quiz title is required")
    @Size(max = 150, message = "Quiz title must be at most 150 characters")
    private String title;

    @Size(max = 500, message = "Description must be at most 500 characters")
    private String description;

    @NotNull(message = "Total questions are required")
    @Min(value = 1, message = "There must be at least 1 question")
    private Long totalQuestions;

    @NotNull(message = "Time limit is required")
    @Min(value = 1, message = "Time limit must be at least 1 minute")
    private Integer timeLimit;

    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Set<QuestionDTO> questions;
}
