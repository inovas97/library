package com.example.library.dto;

import jakarta.json.bind.annotation.JsonbProperty;

public class AuthorDTO {
    private String name;
    @JsonbProperty("birth_year")
    private Integer birthYear;
    @JsonbProperty("death_year")
    private Integer deathYear;

    public AuthorDTO() {
    }

    public AuthorDTO(String name, int birthYear, int deathYear) {
        this.name = name;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }
}
