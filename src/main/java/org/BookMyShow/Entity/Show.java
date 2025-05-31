package org.BookMyShow.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "show")
public class Show {

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
    @JoinColumn(name="seat_id",nullable = false)
    private List<Seat> seats = new ArrayList<>();

    public void addSeat(Seat seat){
        seat.setShow(this);
        this.seats.add(seat);
    }

    public Seat getSeatByNumber(String seatNumber){
        return seats.stream().filter(seat -> seat.getSeatNumber().equals(seatNumber)).findFirst().orElse(null);
    }





}
