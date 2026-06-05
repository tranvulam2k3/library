package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    Long userId;

    @Column(name = "full_name", nullable = false, length = 100)
    String fullName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    String email;

    @Column(name = "phone", length = 20)
    String phone;

    @Column(name = "address", length = 255)
    String address;

    @Column(name = "username", nullable = false, unique = true, length = 50)
    String username;

    @Column(name = "password_hash", nullable = false, length = 255)
    String passwordHash;

    @Column(name = "role", nullable = false, length = 30)
    @Builder.Default
    String role = "READER";

    @Column(name = "status", nullable = false, length = 30)
    @Builder.Default
    String status = "ACTIVE";

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
