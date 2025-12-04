package com.prashant.quizforge.server.controller;

import com.prashant.quizforge.server.dto.LoginRequestDTO;
import com.prashant.quizforge.server.dto.LoginResponseDTO;
import com.prashant.quizforge.server.dto.UserCreateDTO;
import com.prashant.quizforge.server.dto.UserResponseDTO;
import com.prashant.quizforge.server.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginDTO){
        LoginResponseDTO loginResponseDTO = authService.login(loginDTO);

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> singUp(@Valid @RequestBody UserCreateDTO userCreateDTO) {
        UserResponseDTO userCreated = authService.signUp(userCreateDTO);

        return new ResponseEntity<>(userCreated, HttpStatus.CREATED);
    }




}
