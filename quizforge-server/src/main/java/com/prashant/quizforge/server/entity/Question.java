package com.prashant.quizforge.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String questionText;

    @Column(nullable = false, length = 100)
    private String optionA;

    @Column(nullable = false, length = 100)
    private String optionB;

    @Column(nullable = false, length = 100)
    private String optionC;

    @Column(nullable = false, length = 100)
    private String optionD;

    @Column(nullable = false, length = 100)
    private String correctAnswer;

    @NotNull
    @Min(0)
    @Column(nullable = false, columnDefinition = "INT CHECK (marks >= 0)")
    private Integer marks;

    @NotNull
    @Column(nullable = false)
    private Integer questionNumber;

    private String explanation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    @ToString.Exclude
    private Quiz quiz;
}
