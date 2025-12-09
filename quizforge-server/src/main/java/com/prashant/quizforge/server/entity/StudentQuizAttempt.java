package com.prashant.quizforge.server.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class StudentQuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    @ToString.Exclude
    private Quiz quiz;

    private LocalDateTime startedAt;
    private LocalDateTime finishedAt;
    private int score;

    @OneToMany(mappedBy = "studentQuizAttempt", cascade = CascadeType.ALL)
    private Set<StudentAnswer> studentAnswers = new HashSet<>();

}
