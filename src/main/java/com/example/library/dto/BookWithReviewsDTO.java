package com.example.library.dto;

public class BookWithReviewsDTO extends BookDTO {
    private Double averageRating;
    private String[] comments;

    public BookWithReviewsDTO() {
        super();
    }

    public Double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }
    public String[] getComments() {
        return comments;
    }
    public void setComments(String[] comments) {
        this.comments = comments;
    }
}
