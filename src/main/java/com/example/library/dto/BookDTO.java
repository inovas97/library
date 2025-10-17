package com.example.library.dto;


import jakarta.json.bind.annotation.JsonbProperty;

public class BookDTO {
    private int id;
    private String title;
    private AuthorDTO[] authors;
    private String[] languages;
    @JsonbProperty("download_count")
    private Integer downloadCount;

    public BookDTO() {
    }

    public BookDTO(String title, AuthorDTO[] authors, String[] languages, int id, int downloadCount) {
        this.title = title;
        this.authors = authors;
        this.languages = languages;
        this.id = id;
        this.downloadCount = downloadCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public AuthorDTO[] getAuthors() {
        return authors;
    }

    public void setAuthors(AuthorDTO[] authors) {
        this.authors = authors;
    }

    public String[] getLanguages() {
        return languages;
    }

    public void setLanguages(String[] languages) {
        this.languages = languages;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }
}
