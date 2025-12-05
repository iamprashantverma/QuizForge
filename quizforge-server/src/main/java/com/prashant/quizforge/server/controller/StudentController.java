package com.prashant.quizforge.server.controller;

import com.prashant.quizforge.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {
    private final UserService userService;

}
