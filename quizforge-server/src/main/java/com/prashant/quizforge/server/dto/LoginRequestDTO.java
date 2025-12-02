package com.prashant.quizforge.server.dto;


import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;

}