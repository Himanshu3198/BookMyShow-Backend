package org.BookMyShow.Service;

import jakarta.transaction.Transactional;
import org.BookMyShow.Entity.BookingHistory;
import org.BookMyShow.Entity.MovieShow;
import org.BookMyShow.Entity.User;
import org.BookMyShow.Enum.BookingStatus;
import org.BookMyShow.Enum.PaymentType;
import org.BookMyShow.Exception.ResourceNotFoundException;
import org.BookMyShow.Repository.BookingHistoryRepository;
import org.BookMyShow.Repository.ShowRepository;
import org.BookMyShow.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingHistoryService {

    private final BookingHistoryRepository bookingHistoryRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    public BookingHistoryService(BookingHistoryRepository bookingHistoryRepository,UserRepository userRepository,ShowRepository showRepository){
        this.bookingHistoryRepository = bookingHistoryRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
    }

    @Transactional
    public BookingHistory addBookingHistory(String bookingId, Long userId, Long showId, String seatNumber, Double amount, BookingStatus bookingStatus, PaymentType paymentStatus){

        User user = userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User not found with Id: "+userId));
        MovieShow movieShow = showRepository.findById(showId)
                .orElseThrow(()->new ResourceNotFoundException("Show not found with Id: "+showId));
        BookingHistory bookingHistory = new BookingHistory()
                .setBookingId(bookingId)
                .setBookingStatus(bookingStatus)
                .setUser(user)
                .setShow(movieShow)
                .setAmount(amount)
                .setPaymentStatus(paymentStatus)
                .setSeatNumber(seatNumber);
        return bookingHistoryRepository.save(bookingHistory);

    }
    @Transactional
    public BookingHistory updateBookingHistory(BookingHistory bookingHistory){
        return bookingHistoryRepository.save(bookingHistory);
    }
    public List<BookingHistory> getHistoryForUser(Long userId){
        return bookingHistoryRepository.findByUserId(userId);
    }

    public BookingHistory getHistoryByBookingId(String bookingId){

        return bookingHistoryRepository.findByBookingId(bookingId)
                .orElseThrow(()->new ResourceNotFoundException("Booking not found with id: "+bookingId));
    }
}
