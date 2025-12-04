package com.prashant.quizforge.server.service;


import com.prashant.quizforge.server.entity.User;

public interface JWTService {
    String generateAccessToken(User user);

    String generateRefreshToken(User user);

    String getUserIdFromToken(String token);
}
