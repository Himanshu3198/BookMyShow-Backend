package org.BookMyShow.Entity;

import jakarta.persistence.*;
import org.BookMyShow.Enum.Status;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private MovieShow show;

    public Seat() {
    }

    public Long getId() {
        return id;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Status getStatus() {
        return status;
    }

    public MovieShow getShow() {
        return show;
    }

    //
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setShow(MovieShow show) {
        this.show = show;
    }
}
