package com.example.demo.dto.borrow_ticket;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BorrowTicketResponse {
    Long ticketId;
    Long readerId;
    Long librarianId;
    LocalDate borrowDate;
    LocalDate dueDate;
    String status;
    String note;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
