package org.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movie_show")
public class MovieShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "show_time")
    @JsonProperty("showTime")
    private String showTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    public MovieShow() {}

    //
    public void addSeat(Seat seat) {
        seat.setShow(this);
        this.seats.add(seat);
    }

    public Seat getSeatByNumber(String seatNumber) {
        return seats.stream()
                .filter(seat -> seat.getSeatNumber().equals(seatNumber))
                .findFirst()
                .orElse(null);
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getShowTime() {
        return showTime;
    }

    public Movie getMovie() {
        return movie;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Theater getTheater() {
        return theater;
    }

    //  Setters
    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }
}
