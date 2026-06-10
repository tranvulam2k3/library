package com.example.demo.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateUserRequest {
    private String fullName;

    private String phone;

    private String address;

    private String role;

    private String status;
}
