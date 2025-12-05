package com.prashant.quizforge.server.repositoriy;

import com.prashant.quizforge.server.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {

    Page<Question> findByQuizId(Long quizId, Pageable pageable);
}
