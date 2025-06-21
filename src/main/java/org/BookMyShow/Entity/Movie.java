package org.BookMyShow.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name="movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="cast",nullable = true)
    private String cast;

    @Column(name="genre",nullable = true)
    private String genre;

    public Movie() {
    }

    // ✅ Parameterized constructor
    public Movie(String title, String cast, String genre) {
        this.title = title;
        this.cast = cast;
        this.genre = genre;
    }

//    getters and setters

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCast() {
        return cast;
    }

    public String getGenre() {
        return genre;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
