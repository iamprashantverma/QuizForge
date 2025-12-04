package com.prashant.quizforge.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@Slf4j
@EnableAsync
public class QuizForgeServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuizForgeServerApplication.class, args);
		log.info("QuizForge Application is Running Successfully ");
	}

}
