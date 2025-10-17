package com.example.library.dto;

public class ReviewDTO {
    int bookId;
    int rating;
    String comment;

    public ReviewDTO() {}

    public ReviewDTO(int bookId, int rating, String comment) {
        this.bookId = bookId;
        this.rating = rating;
        this.comment = comment;
    }

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "ReviewDTO{" +
                "bookId=" + bookId +
                ", rating=" + rating +
                ", comment='" + comment + '\'' +
                '}';
    }
}
