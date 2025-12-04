package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.dto.UserResponseDTO;
import com.prashant.quizforge.server.entity.User;
import com.prashant.quizforge.server.exception.UserNotFoundException;
import com.prashant.quizforge.server.repositoriy.UserRepository;
import com.prashant.quizforge.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public List<UserResponseDTO> getAllActiveUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .filter(User::getActive)
                .map(k->modelMapper.map(k,UserResponseDTO.class))
                .toList();

    }

    @Override
    public UserResponseDTO updateProfilePicture(String email, MultipartFile image) {
        return null;
    }

    @Override
    public UserResponseDTO updateEmailVerified(String email, Boolean verified) {
        return null;
    }

    @Override
    public UserDetails getUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail).orElseThrow(()->
                 new UserNotFoundException("User not registered with email:"+ userEmail));
    }

}
