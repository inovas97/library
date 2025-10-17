package com.example.library.service;

import com.example.library.dto.*;
import com.example.library.exception.GutendexException;
import com.example.library.exception.NotFound;
import com.example.library.client.GutendexClient;
import com.example.library.dao.ReviewDAO;
import com.example.library.entity.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class BookService {

    @Inject
    GutendexClient gutendexClient;

    @Inject
    ReviewDAO reviewDAO;

    public List<BookDTO> getBooks() {
        return gutendexClient.getBooks();
    }

    // Part 1: Search books via Gutendex API
    public SearchResponseDTO searchBooks(String term) throws GutendexException {
        return new SearchResponseDTO(gutendexClient.searchBooks(term));
    }


    // Part 2: Add a review
    public void addReview(ReviewDTO reviewDTO) throws NotFound, GutendexException {
        // make sure that book exists
        this.gutendexClient.findBookById(reviewDTO.getBookId());
        Review review = new Review();
        review.setBookId(reviewDTO.getBookId());
        review.setRating(reviewDTO.getRating());
        review.setComment(reviewDTO.getComment());
        reviewDAO.save(review);
    }

    // Part 2-Extension: Get N Top rated Books
    public List<BookDTO> getTopRatedBooks(int limit) throws NotFound, GutendexException {
        List<Integer> ids = reviewDAO.getTopRatedBooks(limit);
        if (ids.isEmpty()) {
            return List.of();
        }
        return gutendexClient.findBookByIds(ids);
    }

    public List<Review> findAll() {
        return reviewDAO.findAll();
    }

    public List<Review> findByBookId(int bookId) {
        return reviewDAO.findByBookId(bookId);
    }

    // Part 3: Get book with reviews
    public BookWithReviewsDTO getBookWithReviews(int bookId) throws NotFound, GutendexException {
        BookWithReviewsDTO bookWithReviewsDTO = this.gutendexClient.findBookById(bookId);
        bookWithReviewsDTO.setAverageRating(this.reviewDAO.getAverageRating(bookId));
        bookWithReviewsDTO.setComments(this.reviewDAO.getComments(bookId));
        return bookWithReviewsDTO;
    }

    public List<MonthAverage> getAverageRatingPerMonth(int bookId) {
        return this.reviewDAO.getAverageRatingPerMonth(bookId);
    }

}
