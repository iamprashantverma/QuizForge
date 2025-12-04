package com.prashant.quizforge.server.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
    UserDetails getUserByEmail(String userEmail);
}
