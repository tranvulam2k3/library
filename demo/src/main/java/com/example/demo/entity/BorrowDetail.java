package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrow_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BorrowDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_detail_id")
    Long borrowDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ticket_id", nullable = false)
    BorrowTicket borrowTicket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    Book book;

    @Column(name = "quantity", nullable = false)
    @Builder.Default
    Integer quantity = 1;

    @Column(name = "return_date")
    LocalDate returnDate;

    @Column(name = "status", nullable = false, length = 30)
    @Builder.Default
    String status = "BORROWING";

    @Column(name = "fine_amount", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    BigDecimal fineAmount = BigDecimal.ZERO;

    @Column(name = "fine_reason", length = 255)
    String fineReason;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
