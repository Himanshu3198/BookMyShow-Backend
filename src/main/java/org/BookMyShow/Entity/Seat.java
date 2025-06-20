package org.BookMyShow.Entity;

import jakarta.persistence.*;
import org.BookMyShow.Enum.Status;

@Entity
@Table(name="seats")
public class Seat {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   @Column(name="seat_number", nullable = false, unique = true)
    private String seatNumber;

   @Enumerated(EnumType.STRING)
   @Column(nullable = false)
    private Status status;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name="show_id",nullable = false)
    private MovieShow show;

//   Getter and Setter



    public String getSeatNumber() {
        return seatNumber;
    }

    public Status getStatus() {
        return status;
    }

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
