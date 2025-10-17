package com.example.library.dao;

import com.example.library.dto.MonthAverage;
import com.example.library.entity.Review;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
@Transactional
public class ReviewDAO {

    @PersistenceContext(unitName = "bookPU")
    EntityManager em;

    public void save(Review review) {
        em.persist(review);
    }

    public List<Review> findAll() {
        Query query = em.createNamedQuery("Review.findAll", Review.class);
        return (List<Review>) query.getResultList();
    }

    public List<Review> findByBookId(int bookId) {
        Query query = em.createNamedQuery("Review.findByBookId", Review.class);
        query.setParameter("bookId", bookId);
        return (List<Review>) query.getResultList();
    }

    public Double getAverageRating(int bookId) {
        Query query = em.createNamedQuery("Review.getAverageRating", Double.class);
        query.setParameter("bookId", bookId);
        return (Double) query.getSingleResult();
    }

    public List<Integer> getTopRatedBooks(int limit) {
        Query query = em.createNamedQuery("Review.getTopRatedBooks", Integer.class);
        query.setMaxResults(limit);
        return (List<Integer>) query.getResultList();
    }

    public String[] getComments(int bookId) {
        Query query = em.createNamedQuery("Review.getComments", String.class);
        query.setParameter("bookId", bookId);
        List<String> comments = (List<String>) query.getResultList();
        return comments.toArray(new String[comments.size()]);
    }

    public List<MonthAverage> getAverageRatingPerMonth(int bookId) {
        Query query = em.createNamedQuery("Review.getAverageRatingPerMonth", MonthAverage.class);
        query.setParameter("bookId", bookId);
        return (List<MonthAverage>) query.getResultList();
    }
}
