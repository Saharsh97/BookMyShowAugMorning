package org.example.bookmyshowaugmorning.services;


import org.example.bookmyshowaugmorning.exceptions.SeatAlreadyBookedException;
import org.example.bookmyshowaugmorning.exceptions.ShowNotFoundException;
import org.example.bookmyshowaugmorning.exceptions.ShowSeatTypeNotFoundException;
import org.example.bookmyshowaugmorning.exceptions.UserNotFoundException;
import org.example.bookmyshowaugmorning.models.*;
import org.example.bookmyshowaugmorning.models.enums.ShowSeatStatus;
import org.example.bookmyshowaugmorning.repositories.BookingRepository;
import org.example.bookmyshowaugmorning.repositories.ShowRepository;
import org.example.bookmyshowaugmorning.repositories.ShowSeatRepository;
import org.example.bookmyshowaugmorning.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class BookingServiceTest {

    private BookingService bookingService;

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;

    private PriceCalculationService priceCalculationService;
    private BookingRepository bookingRepository;

    @Before
    public void setup(){
        userRepository = Mockito.mock(UserRepository.class);    // giving you a dummy implementation for UserRepository.class
        showRepository = Mockito.mock(ShowRepository.class);
        showSeatRepository = Mockito.mock(ShowSeatRepository.class);

        bookingService = new BookingService(userRepository, showRepository, showSeatRepository, priceCalculationService, bookingRepository);
    }

    @Test
    public void createBooking_success() throws UserNotFoundException, SeatAlreadyBookedException, ShowNotFoundException, ShowSeatTypeNotFoundException {
        // Arrange
        User hardcodedUser = new User();
        hardcodedUser.setName("Sunil");
        hardcodedUser.setPhone("7827569029");
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(hardcodedUser));

        Show hardcodedShow = new Show();
        Movie movie = new Movie();
        movie.setName("James Bond");
        hardcodedShow.setMovie(movie);
        Mockito.when(showRepository.findById(anyLong())).thenReturn(Optional.of(hardcodedShow));

        ShowSeat showSeat1 = new ShowSeat();
        ShowSeat showSeat2 = new ShowSeat();
        showSeat1.setStatus(ShowSeatStatus.AVAILABLE);
        showSeat2.setStatus(ShowSeatStatus.AVAILABLE);
        List<ShowSeat> showSeats = new ArrayList<>(List.of(showSeat1, showSeat2));
        Mockito.when(showSeatRepository.findAllById(anyList())).thenReturn(showSeats);


        // Act
        Booking resultBooking = bookingService.createBooking(100L, 1000L, List.of(1L, 2L));

        // Assert
        Assert.assertNotNull(resultBooking);
        Assert.assertNotNull(resultBooking.getId());
    }








    @Test
    public void createBooking_failure_userNotFound(){
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void createBooking_failure_seatNotAvailable(){
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void createBooking_success_seatLockedGapMoreThan10mins(){
        // Arrange

        // Act

        // Assert
    }

    @Test
    public void createBooking_failure_seatLockedGapLessThan10mins(){
        // Arrange

        // Act

        // Assert
    }
}
