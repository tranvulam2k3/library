package com.example.demo.dto.publisher;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PublisherRequest {
    @NotBlank(message = "PUBLISHER_NAME_REQUIRED")
    @Size(max = 250, message = "PUBLISHER_NAME_TOO_LONG")
    String publisherName;

    String email;
    String phone;
    String address;
}
