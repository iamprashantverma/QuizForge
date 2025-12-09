package com.prashant.quizforge.server.dto;

import lombok.Data;

@Data
public class StudentAnswerDTO {
    private Long id;
    private Long questionId;
    private String selectedOption;
    private boolean correct;
}
