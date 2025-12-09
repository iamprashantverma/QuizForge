package com.prashant.quizforge.server.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
public class UserQuizAttempt {
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

    @OneToMany(mappedBy = "userQuizAttempt", cascade = CascadeType.ALL)
    private Set<UserAnswer> userAnswers;

}
