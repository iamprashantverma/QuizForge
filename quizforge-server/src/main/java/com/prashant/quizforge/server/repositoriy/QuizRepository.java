package com.prashant.quizforge.server.repositoriy;

import com.prashant.quizforge.server.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizRepository extends JpaRepository<Quiz,Long> {

    boolean existsByLink(String link);
}
