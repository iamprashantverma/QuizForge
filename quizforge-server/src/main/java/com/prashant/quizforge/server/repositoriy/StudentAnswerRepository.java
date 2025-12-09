package com.prashant.quizforge.server.repositoriy;

import com.prashant.quizforge.server.entity.Question;
import com.prashant.quizforge.server.entity.StudentAnswer;
import com.prashant.quizforge.server.entity.StudentQuizAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAnswerRepository extends JpaRepository<StudentAnswer,Long> {
    boolean existsByStudentQuizAttemptAndQuestion(StudentQuizAttempt quizAttempt, Question question);
}
