package com.example.library.client;

import com.example.library.cache.CacheInterceptor;
import com.example.library.cache.CacheMechanism;
import com.example.library.cache.Cacheable;
import com.example.library.exception.GutendexException;
import com.example.library.exception.NotFound;
import com.example.library.dto.BookDTO;
import com.example.library.dto.BookWithReviewsDTO;
import com.example.library.dto.GutendexResponse;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Interceptors(CacheInterceptor.class)
public class GutendexClient {
    private static final String API_URL = "https://gutendex.com/books/";

    @Cacheable()
    public List<BookDTO> getBooks() throws GutendexException {
        System.out.println("Fetching books from Gutendex");
        List<BookDTO> allBooks = new ArrayList<>();
        try (Client client = ClientBuilder.newClient()) {
            GutendexResponse response;
            int page = 1;
            do {
                response = client.target(API_URL)
                        .queryParam("page", page++)
                        .request(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .get(GutendexResponse.class);
                allBooks.addAll(response.getResults());
            } while (response.getNext() != null);
        } catch (Exception e) {
            throw new GutendexException("Error occurred while fetching books from Gutendex");
        }
        return allBooks;
    }

    @Cacheable()
    public List<BookDTO> searchBooks(String term) throws GutendexException {
        System.out.println("Searching for books in the Gutendex library");
        List<BookDTO> books = new ArrayList<>();
        try (Client client = ClientBuilder.newClient()) {
            GutendexResponse response;
            int page = 1;
            do {
                response = client.target(API_URL)
                        .queryParam("page", page++)
                        .queryParam("search", term)
                        .request(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .get(GutendexResponse.class);
                books.addAll(response.getResults());
            } while (response.getNext() != null);
        } catch (Exception e) {
            throw new GutendexException("Error occurred while searching for books in the Gutendex library");
        }
        return books;
    }

    @Cacheable()
    public BookWithReviewsDTO findBookById(int id) throws NotFound, GutendexException {
        System.out.println("Fetching book details from Gutendex");
        try (Client client = ClientBuilder.newClient()) {
            return client.target(API_URL + id)
                    .request(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .get(BookWithReviewsDTO.class);
        } catch (NotFoundException e) {
            throw new NotFound("Book ID does not match any book in the Gutendex library");
        } catch (Exception e) {
            throw new GutendexException("Error occurred while fetching book details from Gutendex");
        }
    }

    @Cacheable()
    public List<BookDTO> findBookByIds(List<Integer> ids) throws NotFound, GutendexException {
        System.out.println("Fetching books from Gutendex");
        List<BookDTO> allBooks = new ArrayList<>();
        try (Client client = ClientBuilder.newClient()) {
            String idsParam = ids.stream().map(String::valueOf).collect(Collectors.joining(","));
            GutendexResponse response;
            int page = 1;
            do {
                response = client.target(API_URL)
                        .queryParam("page", page++)
                        .queryParam("ids", idsParam)
                        .request(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .get(GutendexResponse.class);
                allBooks.addAll(response.getResults());
            } while (response.getNext() != null);
        } catch (NotFoundException e) {
            throw new NotFound("Book id does not match any book in the Gutendex library");
        } catch (Exception e) {
            throw new GutendexException("Error occurred while fetching book details from Gutendex");
        }
        return allBooks;
    }

}
