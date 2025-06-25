package org.BookMyShow.Service;

import jakarta.transaction.Transactional;
import org.BookMyShow.Entity.*;
import org.BookMyShow.Enum.BookingStatus;
import org.BookMyShow.Enum.PaymentType;
import org.BookMyShow.Enum.Status;
import org.BookMyShow.Exception.BookingFailedException;
import org.BookMyShow.Exception.PaymentFailedException;
import org.BookMyShow.Exception.SeatAlreadyBookedException;
import org.BookMyShow.PaymentStrategy.CardPaymentImp;
import org.BookMyShow.PaymentStrategy.PaymentStrategy;
import org.BookMyShow.Repository.BookingRepository;
import org.BookMyShow.Repository.SeatRepository;
import org.BookMyShow.Repository.ShowRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingService {


    private final UserService userService;
    private final ShowService showService;
    private final PaymentService paymentService;
    private final BookingHistoryService bookingHistoryService;
    private final SeatRepository seatRepository;

    public BookingService(BookingRepository bookingRepository,
                          UserService userService,
                          ShowService showService,
                          PaymentService paymentService,
                          BookingHistoryService bookingHistoryService,
                          SeatRepository seatRepository) {
        this.userService = userService;
        this.showService = showService;
        this.paymentService = paymentService;
        this.bookingHistoryService = bookingHistoryService;
        this.seatRepository = seatRepository;
    }

    @Transactional
    public void placeBooking(String seatNumber, Long userId, Long showId, Double amount, PaymentStrategy paymentStrategy) {

        try {
            User user = userService.getUserById(userId);
            MovieShow show = showService.getShowById(showId);
            Seat seat = show.getSeatByNumber(seatNumber);
            if (seat.getStatus() == Status.BOOKED) {
                throw new SeatAlreadyBookedException("Seat is not available: " + seatNumber);
            }
            String bookingId = UUID.randomUUID().toString().substring(0, 8);
            Boolean paymentSuccess = paymentService.processPayment(bookingId, amount, user, new CardPaymentImp());

            if (!paymentSuccess) {
                throw new PaymentFailedException("Payment got failed for booking: " + bookingId);
            }
            seat.setStatus(Status.BOOKED);
            seatRepository.save(seat);
            bookingHistoryService.addBookingHistory(bookingId,
                    userId,
                    showId,
                    seatNumber,
                    amount,
                    BookingStatus.CONFIRMED,
                    PaymentType.UPI);


        } catch (BookingFailedException e) {
            throw new BookingFailedException("Booking has been failed for bookingId");
        }catch (DataAccessException dae){
            throw new RuntimeException("Database error occurred during booking:"+dae.getMessage(),dae);
        }
    }

    @Transactional
    public void cancelBooking(String bookingId){

        BookingHistory bookingRecord = bookingHistoryService.getHistoryByBookingId(bookingId);
        MovieShow movieShow = bookingRecord.getShow();
        Seat seat = movieShow.getSeatByNumber(bookingRecord.getSeatNumber());

        if(seat.getStatus() == Status.BOOKED){
            seat.setStatus(Status.AVAILABLE);
        }
        Double amount = bookingRecord.getAmount();
        User user = bookingRecord.getUser();
        user.setWalletBalance(user.getWalletBalance()+amount);
        bookingRecord.setBookingStatus(BookingStatus.CANCELLED);
        bookingRecord.setBookingTime(LocalDateTime.now());
        bookingHistoryService.updateBookingHistory(bookingRecord);

    }
}
