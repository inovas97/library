package com.example.library.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "REVIEW")
@NamedQueries({
        @NamedQuery(name = "Review.findAll", query = "SELECT r FROM Review r"),
        @NamedQuery(name = "Review.findByBookId", query = "SELECT r FROM Review r WHERE r.bookId=:bookId"),
        @NamedQuery(name = "Review.getAverageRating", query = "SELECT AVG(r.rating) FROM Review r WHERE r.bookId=:bookId"),
        @NamedQuery(name = "Review.getTopRatedBooks", query = "SELECT r.bookId FROM Review r GROUP BY r.bookId ORDER BY AVG(r.rating) DESC"),
        @NamedQuery(name = "Review.getComments", query = "SELECT r.comment FROM Review r WHERE r.bookId=:bookId"),
        @NamedQuery( name = "Review.getAverageRatingPerMonth", query = "SELECT " +
                "EXTRACT(YEAR FROM r.createdAt) AS year, EXTRACT(MONTH FROM r.createdAt) AS month, AVG(r.rating) as average " +
                "FROM Review r WHERE r.bookId=:bookId GROUP BY EXTRACT(YEAR FROM r.createdAt), EXTRACT(MONTH FROM r.createdAt)")
})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Book ID cannot be empty")
    @Column(name = "BOOKID")
    private Integer bookId;

    @Min(value = 1, message = "Rating must be between 1 and 5")
    @Max(value = 5, message = "Rating must be between 1 and 5")
    @NotNull(message = "Rating cannot be empty")
    @Column(name = "RATING")
    private Integer rating;

    @Size(max = 1000)
    @NotEmpty(message = "Comment cannot be empty")
    @Column(name = "COMMENT", length = 1000)
    private String comment;

    @Column(name = "CREATEDAT")
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}