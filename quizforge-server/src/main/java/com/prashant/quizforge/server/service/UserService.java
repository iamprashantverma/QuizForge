package com.prashant.quizforge.server.service;

import com.prashant.quizforge.server.dto.StudentResponseDTO;
import com.prashant.quizforge.server.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService {

    /**
     * Fetch UserDetails by email (used by Spring Security for authentication)
     *
     * @param email the user's email
     * @return UserDetails object for authentication
     */
    UserDetails getUserByEmail(String email);

    /**
     * Get all active users.
     *
     * @return list of active users
     */
    List<StudentResponseDTO> getAllActiveUsers();

    /**
     * Update user's profile picture.
     *
     * @param email the email of the user to update
     * @param image the new profile picture file
     * @return updated user DTO
     */
    StudentResponseDTO updateProfilePicture(String email, MultipartFile image);

    /**
     * Update user's email verified status.
     *
     * @param email the email of the user to update
     * @param verified the new email verified status
     * @return updated user DTO
     */
    StudentResponseDTO updateEmailVerified(String email, Boolean verified);

    User getCurrentUser();
}
