package com.example.library.rest;

import com.example.library.dto.AveragePerMonthDTO;
import com.example.library.exception.GutendexException;
import com.example.library.exception.NotFound;
import com.example.library.dto.ReviewDTO;
import com.example.library.service.BookService;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.stream.Collectors;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    @Inject
    private BookService bookService;

    @GET
    @Path("/search")
    public Response search(@QueryParam("term") String term) {
        try {
            return Response.ok(bookService.searchBooks(term)).build();
        } catch (GutendexException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Application Server Error").build();
        }
    }

    @GET
    @Path("topRated")
    public Response topRated(@QueryParam("limit") int limit) {
        try {
            return Response.ok(bookService.getTopRatedBooks(limit)).build();
        } catch (GutendexException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        } catch (Exception e) {
//            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Application Server Error").build();
        }
    }

    @GET
    @Path("/all")
    public Response all() {
        try {
            return Response.ok(bookService.getBooks()).build();
        } catch (Exception e) {
            System.err.print(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Application Server Error").build();
        }
    }

    @POST
    @Path("/review")
    public Response review(ReviewDTO review) {
        try {
            bookService.addReview(review);
            return Response.ok("Review has been successfully added").build();
        } catch (NotFound e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        }
        catch (GutendexException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        } catch (ConstraintViolationException e) {
            String error = e.getConstraintViolations()
                    .stream()
                    .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                    .collect(Collectors.joining(", "));
            return Response.status(Response.Status.BAD_REQUEST.getStatusCode(), error).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Application Server Error").build();
        }
    }

    @GET
    @Path("/allReviews")
    public Response reviews() {
        return Response.ok(bookService.findAll()).build();
    }

    @GET
    @Path("/details")
    public Response details(@QueryParam("id") int id) {
        try {
            return Response.ok(bookService.getBookWithReviews(id)).build();
        } catch (NotFound e) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode(), e.getMessage()).build();
        } catch (GutendexException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), e.getMessage()).build();
        } catch (Exception e) {
//            System.err.println(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), "Application Server Error").build();
        }
    }

    @GET
    @Path("/reviews")
    public Response reviews(@QueryParam("id") int id) {
        return Response.ok(bookService.findByBookId(id)).build();
    }

    @GET
    @Path("/averagePerMonth")
    public Response averagePerMonth(@QueryParam("id") int id) {
        AveragePerMonthDTO response = new AveragePerMonthDTO();
        response.setBookId(id);
        response.setMonthAverage(bookService.getAverageRatingPerMonth(id));
        return Response.ok(response).build();
    }
}
