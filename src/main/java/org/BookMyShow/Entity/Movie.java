package org.BookMyShow.Entity;

import jakarta.persistence.*;

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





}
