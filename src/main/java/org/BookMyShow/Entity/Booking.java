package org.BookMyShow.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", unique = true, nullable = false)
    @JsonProperty(value = "seatNumber")
    private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private MovieShow show;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "amount", nullable = false)
    @JsonProperty(value = "amount")
    private Double amount;

    public Booking(String seatNumber, User user, MovieShow show, Double amount) {
        this.seatNumber = seatNumber;
        this.user = user;
        this.show = show;
        this.amount = amount;
    }

    public MovieShow getShow() {
        return show;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Double getAmount() {
        return amount;
    }
}
