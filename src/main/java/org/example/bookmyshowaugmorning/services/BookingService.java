package org.example.bookmyshowaugmorning.services;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.Setter;
import org.example.bookmyshowaugmorning.models.Booking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingService {

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(long userId, long showId, List<Long> showSeatIds){
        // 1. get the user from the userID
        // 2. get the show from the showId
        // 3. get the ShowSeat object, from the showSeatIds

        // 4. check if the chosen ShowSeat objects are AVAILABLE
        // 5. BLOCK these chosen seats, if they were AVAILABLE.
        // BLOCK for 10 mins.
        // 6. update the showSeat objects in DB as BLOCKED,
        // with the current timestamp as lockedAt.
        // 7. get the total amount
        // 8. create the booking object, fill all the attributes.
        return null;
    }
}
