package com.example.demo.dto.book;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookResponse {
    Long bookId;
    String title;
    String authorName;
    String publisherName;
    String isbn;
    Integer publicationYear;
    String description;
    Integer totalQuantity;
    Integer availableQuantity;
    String shelfLocation;
    String status;
    String categoryName;
}
