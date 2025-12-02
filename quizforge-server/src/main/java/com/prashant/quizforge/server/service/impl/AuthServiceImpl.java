package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.dto.LoginRequestDTO;
import com.prashant.quizforge.server.dto.LoginResponseDTO;
import com.prashant.quizforge.server.dto.UserCreateDTO;
import com.prashant.quizforge.server.dto.UserResponseDTO;
import com.prashant.quizforge.server.entity.User;
import com.prashant.quizforge.server.exception.UserAlreadyExistsException;
import com.prashant.quizforge.server.exception.UserNotFoundException;
import com.prashant.quizforge.server.repositoriy.UserRepository;
import com.prashant.quizforge.server.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserResponseDTO signUp(UserCreateDTO userCreateDTO) {
        // Check if user already exists
        userRepository.findByEmail(userCreateDTO.getEmail())
                .ifPresent(u -> {
                    log.warn("Signup failed: email {} already exists", userCreateDTO.getEmail());
                    throw new UserAlreadyExistsException("Email already registered: " + userCreateDTO.getEmail());
                });
        User toBeCreated = convertToEntity(userCreateDTO);

        // hash The password later before saving into the DB
        User savedUser = userRepository.save(toBeCreated);

        return convertToUserDTO(savedUser);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not registered with email: " + loginRequestDTO.getEmail()
                ));

        return LoginResponseDTO.builder().build();
    }

    private User convertToEntity(UserCreateDTO userCreateDTO) {
        return  modelMapper.map(userCreateDTO,User.class);
    }

    private UserResponseDTO convertToUserDTO(User user) {
        return modelMapper.map(user,UserResponseDTO.class);
    }

}
