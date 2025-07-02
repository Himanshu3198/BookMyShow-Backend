package org.BookMyShow.Entity;

import jakarta.persistence.*;
import org.BookMyShow.Enum.BookingStatus;
import org.BookMyShow.Enum.PaymentType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "booking_history")
public class BookingHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "booking_id", unique = true, nullable = false)
    private String bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id", nullable = false)
    private MovieShow show;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "amount_paid")
    private Double amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private BookingStatus bookingStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;

    @CreationTimestamp
    @Column(name = "booking_time", nullable = false)
    private LocalDateTime bookingTime;

    // constructors, getters, setters


    public Long getId() {
        return id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public User getUser() {
        return user;
    }

    public MovieShow getShow() {
        return show;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public PaymentType getpaymentType() {
        return paymentType;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public BookingHistory setBookingId(String bookingId) {
        this.bookingId = bookingId;
        return this;
    }

    public BookingHistory setUser(User user) {
        this.user = user;
        return this;
    }

    public BookingHistory setShow(MovieShow show) {
        this.show = show;
        return this;
    }

    public BookingHistory setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
        return this;
    }

    public BookingHistory setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public BookingHistory setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
        return this;
    }

    public BookingHistory setpaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public BookingHistory setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
        return this;
    }
}
