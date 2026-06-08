package com.example.demo.mapping.user;

import com.example.demo.dto.user.CreateUserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapping {

    /**
     * CreateUserRequest -> User Entity
     */
    public User toEntity(CreateUserRequest request) {

        if (request == null) {
            return null;
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setAddress(request.getAddress());
        user.setUsername(request.getUsername());
        return user;
    }

    /**
     * User Entity -> UserResponse
     */
    public UserResponse toResponse(User user) {

        if (user == null) {
            return null;
        }

        return UserResponse.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .address(user.getAddress())
                .username(user.getUsername())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }
}