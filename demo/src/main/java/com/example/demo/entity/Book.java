package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    Long bookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    Publisher publisher;

    @Column(name = "title", nullable = false, length = 200)
    String title;

    @Column(name = "author_name", length = 100)
    String authorName;

    @Column(name = "publisher_name", length = 150)
    String publisherName;

    @Column(name = "isbn", unique = true, length = 50)
    String isbn;

    @Column(name = "publication_year")
    Integer publicationYear;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Column(name = "total_quantity", nullable = false)
    @Builder.Default
    Integer totalQuantity = 0;

    @Column(name = "available_quantity", nullable = false)
    @Builder.Default
    Integer availableQuantity = 0;

    @Column(name = "shelf_location", length = 100)
    String shelfLocation;

    @Column(name = "status", nullable = false, length = 30)
    @Builder.Default
    String status = "AVAILABLE";

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    LocalDateTime updatedAt;
}
