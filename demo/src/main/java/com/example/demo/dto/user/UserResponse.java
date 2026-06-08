package com.example.demo.dto.user;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {

    private Integer userId;

    private String fullName;

    private String email;

    private String username;

    private String phone;

    private String address;

    private String role;

    private String status;
}
