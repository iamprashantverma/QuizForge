package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.dto.LoginRequestDTO;
import com.prashant.quizforge.server.dto.LoginResponseDTO;
import com.prashant.quizforge.server.dto.UserCreateDTO;
import com.prashant.quizforge.server.dto.UserResponseDTO;
import jakarta.validation.Valid;

public interface AuthService {
    LoginResponseDTO login(@Valid LoginRequestDTO loginDTO);

    UserResponseDTO signUp(@Valid UserCreateDTO userCreateDTO);
}
