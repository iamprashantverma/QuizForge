package com.prashant.quizforge.server.service.impl;

import com.prashant.quizforge.server.entity.User;
import com.prashant.quizforge.server.repositoriy.UserRepository;
import com.prashant.quizforge.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl  implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public UserDetails getUserByEmail(String userEmail) {
        return null;
    }
}
