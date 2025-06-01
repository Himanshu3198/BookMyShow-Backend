package org.BookMyShow.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name="movies")
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;
    @Getter
    @Column(name="title",nullable = false)
    private String title;

    @Column(name="cast",nullable = true)
    private String cast;

    @Column(name="genre",nullable = true)
    private String genre;


    public Movie(String title, String cast,String genre){
        this.title = title;
        this.cast = cast;
        this.genre = genre;
    }



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
}
