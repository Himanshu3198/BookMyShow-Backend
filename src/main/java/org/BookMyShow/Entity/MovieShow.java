package org.BookMyShow.Entity;

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
    @JsonProperty(value = "showTime")
    private String showTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="movie_id",nullable = false)
    private Movie movie;

    @OneToMany(mappedBy = "show",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="theater_id",nullable = false)
    private Theater theater;

    public void addSeat(Seat seat){
        seat.setShow(this);
        this.seats.add(seat);
    }

    public Seat getSeatByNumber(String seatNumber){
        return seats.stream().filter(seat -> seat.getSeatNumber().equals(seatNumber)).findFirst().orElse(null);
    }

    public void setTheater(Theater theater){
        this.theater = theater;
    }

}
