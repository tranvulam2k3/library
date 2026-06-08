package com.example.demo.dto.publisher;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherResponse {
    Integer publisherId;
    String publisherName;
    String email;
    String phone;
    String address;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
