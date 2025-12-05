package com.prashant.quizforge.server.dto;

import jakarta.validation.constraints.*;

import lombok.Data;

@Data
public class QuestionDTO {

    private Long id;

    @NotBlank(message = "Question text is required")
    @Size(max = 150, message = "Question text must be at most 150 characters")
    private String questionText;

    @NotBlank(message = "Option A is required")
    @Size(max = 100, message = "Option A must be at most 100 characters")
    private String optionA;

    @NotBlank(message = "Option B is required")
    @Size(max = 100, message = "Option B must be at most 100 characters")
    private String optionB;

    @NotBlank(message = "Option C is required")
    @Size(max = 100, message = "Option C must be at most 100 characters")
    private String optionC;

    @NotBlank(message = "Option D is required")
    @Size(max = 100, message = "Option D must be at most 100 characters")
    private String optionD;

    @NotBlank(message = "Correct answer is required")
    @Pattern(regexp = "[ABCD]", message = "Correct answer must be one of A, B, C, or D")
    private String correctAnswer;

    @NotNull(message = "Marks are required")
    @Min(value = 0, message = "Marks cannot be negative")
    private Integer marks;

    private String explanation;

    private Long quizId;
}
