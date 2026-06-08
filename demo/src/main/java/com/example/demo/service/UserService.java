package com.example.demo.service;

import com.example.demo.dto.user.CreateUserRequest;
import com.example.demo.dto.user.UpdateUserRequest;
import com.example.demo.dto.user.UserResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.ConflictException;
import com.example.demo.mapping.user.UserMapping;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapping userMapping;

    public UserResponse create(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already exists");
        }

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new ConflictException("Username already exists");
        }

        User user = userMapping.toEntity(request);

        user.setPasswordHash(request.getPassword());
        user.setRole("READER");
        user.setStatus("ACTIVE");

        User savedUser = userRepository.save(user);

        return userMapping.toResponse(savedUser);
    }

    public List<UserResponse> getAllUsers() {

        List<User> users = userRepository.findAll();

        List<UserResponse> responses = new ArrayList<>();

        for (User user : users) {

            UserResponse response = userMapping.toResponse(user);
            responses.add(response);
        }

        return responses;
    }

    public UserResponse update(Integer id, UpdateUserRequest req) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        User user = optionalUser.get();

        user.setFullName(req.getFullName());
        user.setPhone(req.getPhone());
        user.setAddress(req.getAddress());
        user.setRole(req.getRole());
        user.setStatus(req.getStatus());

        user.setUpdatedAt(LocalDateTime.now());

        User updateUser = userRepository.save(user);

        return userMapping.toResponse(updateUser);
    }

    public void delete(Integer id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        user.setStatus("DELETED");
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }
}