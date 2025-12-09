package com.prashant.quizforge.server.repositoriy;

import com.prashant.quizforge.server.entity.Quiz;
import com.prashant.quizforge.server.entity.StudentQuizAttempt;
import com.prashant.quizforge.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentQuizAttemptRepository extends JpaRepository<StudentQuizAttempt,Long> {

    Optional<StudentQuizAttempt>  findByQuizAndUser(com.prashant.quizforge.server.entity.Quiz quiz, User user);
}
