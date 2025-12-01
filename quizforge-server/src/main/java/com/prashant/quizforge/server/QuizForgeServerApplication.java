package com.prashant.quizforge.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class QuizForgeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizForgeServerApplication.class, args);
		log.info("QuizForge Application is Running Successfully ");
	}

}
