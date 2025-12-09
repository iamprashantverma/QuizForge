package com.prashant.quizforge.server.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class StudentQuizAttemptDTO {
    private Long id;
    private Long studentId;
    private Long quizId;
    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private int score;
    private Set<StudentAnswerDTO> studentAnswers;
}
