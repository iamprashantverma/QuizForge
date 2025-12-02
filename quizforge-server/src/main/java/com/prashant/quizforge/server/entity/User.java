package com.prashant.quizforge.server.entity;

import com.prashant.quizforge.server.entity.enums.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(unique = true, nullable = false, length = 20)
    private String email;

    @Column(nullable = false)
    private Boolean active;

    @Column(length = 13)
    private String phoneNumber;

    @Column(nullable = false,length = 60)
    private String password;

    private String profilePictureUrl;

    private Boolean emailVerified;

}
