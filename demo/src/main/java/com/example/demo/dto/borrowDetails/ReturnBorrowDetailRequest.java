package com.example.demo.dto.borrowDetails;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReturnBorrowDetailRequest {

    BigDecimal fineAmount;
    String fineReason;
}
