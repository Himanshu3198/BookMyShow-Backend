package org.BookMyShow.Service;

import org.BookMyShow.Entity.User;
import org.BookMyShow.PaymentStrategy.PaymentStrategy;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
   public Boolean processPayment(String bookingId, Double amount, User user, PaymentStrategy paymentStrategy){
       if(paymentStrategy == null) throw new IllegalArgumentException("No payment method provided");
       return paymentStrategy.pay(bookingId,amount,user);
   }

}
