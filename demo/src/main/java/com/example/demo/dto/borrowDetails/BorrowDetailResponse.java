package com.example.demo.dto.borrowDetails;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowDetailResponse {
    Long borrowDetailId;
    Long ticketId;
    Long bookId;
    String bookTitle;
    Integer quantity;
    LocalDate returnDate;
    String status;
    BigDecimal fineAmount;
    String fineReasone;
    LocalDateTime createdAt;
    LocalDateTime updateAt;
}
