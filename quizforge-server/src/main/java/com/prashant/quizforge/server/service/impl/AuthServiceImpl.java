package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.dto.LoginRequestDTO;
import com.prashant.quizforge.server.dto.LoginResponseDTO;
import com.prashant.quizforge.server.dto.StudentCreateDTO;
import com.prashant.quizforge.server.dto.StudentResponseDTO;
import com.prashant.quizforge.server.entity.User;
import com.prashant.quizforge.server.exception.InvalidCredentialsException;
import com.prashant.quizforge.server.exception.UserAlreadyExistsException;
import com.prashant.quizforge.server.exception.UserNotFoundException;
import com.prashant.quizforge.server.repositoriy.UserRepository;
import com.prashant.quizforge.server.service.AuthService;
import com.prashant.quizforge.server.service.JWTService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTService jwtService;

    @Override
    @Transactional
    public StudentResponseDTO signUp(StudentCreateDTO studentCreateDTO) {
        // Check if user already exists
        userRepository.findByEmail(studentCreateDTO.getEmail())
                .ifPresent(u -> {
                    log.warn("Signup failed: email {} already exists", studentCreateDTO.getEmail());
                    throw new UserAlreadyExistsException("Email already registered: " + studentCreateDTO.getEmail());
                });
        User toBeCreated = convertToEntity(studentCreateDTO);

        // hash the plain text and store in the DB
        String hashPassword =  passwordEncoder.encode(toBeCreated.getPassword());
        toBeCreated.setPassword(hashPassword);

        User savedUser = userRepository.save(toBeCreated);

        return convertToUserDTO(savedUser);
    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        User user = userRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User not registered with email: " + loginRequestDTO.getEmail()
                ));
        String password = loginRequestDTO.getPassword();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }
        // generate the tokens to fulfill next requests
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return  LoginResponseDTO.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();

    }

    private User convertToEntity(StudentCreateDTO studentCreateDTO) {
        return  modelMapper.map(studentCreateDTO,User.class);
    }

    private StudentResponseDTO convertToUserDTO(User user) {
        return modelMapper.map(user, StudentResponseDTO.class);
    }

}
