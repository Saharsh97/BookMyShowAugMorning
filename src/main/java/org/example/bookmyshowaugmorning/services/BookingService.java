package org.example.bookmyshowaugmorning.services;

import org.example.bookmyshowaugmorning.exceptions.SeatAlreadyBookedException;
import org.example.bookmyshowaugmorning.exceptions.ShowNotFoundException;
import org.example.bookmyshowaugmorning.exceptions.ShowSeatTypeNotFoundException;
import org.example.bookmyshowaugmorning.exceptions.UserNotFoundException;
import org.example.bookmyshowaugmorning.models.Show;
import org.example.bookmyshowaugmorning.models.ShowSeat;
import org.example.bookmyshowaugmorning.models.User;
import org.example.bookmyshowaugmorning.models.enums.BookingStatus;
import org.example.bookmyshowaugmorning.models.enums.ShowSeatStatus;
import org.example.bookmyshowaugmorning.repositories.BookingRepository;
import org.example.bookmyshowaugmorning.repositories.ShowRepository;
import org.example.bookmyshowaugmorning.repositories.ShowSeatRepository;
import org.example.bookmyshowaugmorning.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import lombok.Setter;
import org.example.bookmyshowaugmorning.models.Booking;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class BookingService {


    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;

    private final PriceCalculationService priceCalculationService;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, PriceCalculationService priceCalculationService, BookingRepository bookingRepository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.priceCalculationService = priceCalculationService;
        this.bookingRepository = bookingRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public Booking createBooking(long userId, long showId, List<Long> showSeatIds) throws UserNotFoundException, ShowNotFoundException, SeatAlreadyBookedException, ShowSeatTypeNotFoundException {
        // 1. get the user from the userID
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with ID " + userId + " doesn't exist!");
        }
        User user = optionalUser.get();

        // 2. get the show from the showId
        Optional<Show> optionalShow = showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new ShowNotFoundException("Show with ID " + showId + " doesn't exist!");
        }
        Show show = optionalShow.get();

        // 3. get the ShowSeat object, from the showSeatIds
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        if(showSeats.isEmpty()){
            throw new ShowNotFoundException("ShowSeats with IDs " + showSeatIds + " doesn't exist!");
        }

        // 5. check if seats are Available.

        // right now it is 7:30 AM.
        // if the seat was lockedAt 7:00 AM
        // if the time difference between, now - lockedAt > 10 mins, I can use it!

        // seat lockedAt 7:25 AM, can I use it??
        // if the time difference between, now - lockedAt <= 10 mins,
        // this seat is locked for someone else. My booking cannot go further.

        for(ShowSeat showSeat : showSeats){
            ShowSeatStatus status = showSeat.getStatus();
            if(status == ShowSeatStatus.BOOKED){
                throw new SeatAlreadyBookedException("Seat with this ID " + showSeat.getId() + " is already booked!");
            }
            Date currentTime = new Date();
            Date lockedAt = showSeat.getLockedAt();
            Long timeDifference = currentTime.getTime() - lockedAt.getTime();

            if(status == ShowSeatStatus.BLOCKED && TimeUnit.MINUTES.toMinutes(timeDifference) <= 10){
                throw new SeatAlreadyBookedException("Seat with this ID" + showSeat.getId() + " already blocked!");
            }
        }

        // if I reach this point in code,
        // I can say that these seats are meant for my booking!

        // 6. update the showSeat objects in DB as BLOCKED,
        for(ShowSeat showSeat : showSeats){
            showSeat.setLockedAt(new Date());
            showSeat.setStatus(ShowSeatStatus.BLOCKED);
        }
        List<ShowSeat> blockedShowSeats = showSeatRepository.save(showSeats);

        // write Price logic over here.
        // same logic in a lot of places
        // Booking Service, Bill Service, eventService
        // PriceCalculationService -> single place that has the logic for pricing.
        // 7. get the total amount

        int totalAmount = priceCalculationService.getTotalAmount(show, blockedShowSeats);

        // 8. create the booking object, fill all the attributes.
        // create a booking Summary
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setShowSeats(blockedShowSeats);
        booking.setTotalAmount(totalAmount);
        booking.setBookedAt(new Date());
        booking.setPayments(new ArrayList<>());
        booking.setBookingStatus(BookingStatus.PENDING);

        Booking savedBooking = bookingRepository.save(booking);
        return savedBooking;
    }
}
