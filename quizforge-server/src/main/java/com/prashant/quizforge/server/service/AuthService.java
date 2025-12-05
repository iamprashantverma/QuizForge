package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.dto.LoginRequestDTO;
import com.prashant.quizforge.server.dto.LoginResponseDTO;
import com.prashant.quizforge.server.dto.UserCreateDTO;
import com.prashant.quizforge.server.dto.UserResponseDTO;
import jakarta.validation.Valid;


public interface AuthService {

    /**
     * Authenticates a user using the provided login credentials.
     *
     * @param loginDTO a validated DTO containing the user's email and password
     * @return LoginResponseDTO containing the JWT token and basic user information
     */
    LoginResponseDTO login(@Valid LoginRequestDTO loginDTO);

    /**
     * Registers a new user in the system.
     *
     * @param userCreateDTO a validated DTO containing user registration details
     * @return UserResponseDTO containing the created user's information
     */
    UserResponseDTO signUp(@Valid UserCreateDTO userCreateDTO);
}
