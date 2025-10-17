package com.example.library.dto;

import java.util.List;

public class SearchResponseDTO {
    private List<BookDTO> books;

    public SearchResponseDTO() {}

    public SearchResponseDTO(List<BookDTO> books) {
        this.books = books;
    }
    public List<BookDTO> getBooks() {
        return books;
    }
    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }
}
