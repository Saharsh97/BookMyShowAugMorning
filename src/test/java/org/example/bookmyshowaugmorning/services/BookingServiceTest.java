package org.example.bookmyshowaugmorning.services;

import org.example.bookmyshowaugmorning.exceptions.SeatAlreadyBookedException;
import org.example.bookmyshowaugmorning.exceptions.ShowNotFoundException;
import org.example.bookmyshowaugmorning.exceptions.ShowSeatTypeNotFoundException;
import org.example.bookmyshowaugmorning.exceptions.UserNotFoundException;
import org.example.bookmyshowaugmorning.models.Booking;
import org.example.bookmyshowaugmorning.models.Show;
import org.example.bookmyshowaugmorning.models.ShowSeat;
import org.example.bookmyshowaugmorning.models.User;
import org.example.bookmyshowaugmorning.models.enums.ShowSeatStatus;
import org.example.bookmyshowaugmorning.repositories.*;
import org.example.bookmyshowaugmorning.services.stubs.BookingRepositoryStub;
import org.example.bookmyshowaugmorning.services.stubs.ShowSeatRepositoryStub;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;

@SpringBootTest
class BookingServiceTest {

    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ShowRepository showRepository;

    @Mock
    private PriceCalculationService priceCalculationService;

    @Mock
    private ShowSeatRepository showSeatRepository;;

    private ShowSeatRepositoryStub showSeatRepositoryStub;
    private BookingRepositoryStub bookingRepositoryStub;


    @BeforeEach
    void setUp() {
//        bookingService = new BookingService(userRepository, showRepository, showSeatRepository, priceCalculationService, bookingRepository);

        ShowSeat sampleSeat1 = new ShowSeat();
        sampleSeat1.setStatus(ShowSeatStatus.AVAILABLE);
        sampleSeat1.setLockedAt(new Date());
        ShowSeat sampleSeat2 = new ShowSeat();
        sampleSeat2.setStatus(ShowSeatStatus.AVAILABLE);
        sampleSeat2.setLockedAt(new Date());
        List<ShowSeat> showSeats = new ArrayList<>(List.of(sampleSeat1, sampleSeat2));

        showSeatRepositoryStub = new ShowSeatRepositoryStub(showSeats);
        bookingRepositoryStub = new BookingRepositoryStub();
        bookingService = new BookingService(userRepository, showRepository, showSeatRepositoryStub, priceCalculationService, bookingRepositoryStub);
    }

    @AfterEach
    void tearDown() {

    }

//    @Test
//    void createBooking() throws ShowSeatTypeNotFoundException, UserNotFoundException, SeatAlreadyBookedException, ShowNotFoundException {
//        // Arrange
//        User user = new User();
//        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
//
//        Show show = new Show();
//        Mockito.when(showRepository.findById(anyLong())).thenReturn(Optional.of(show));
//
//        ShowSeat sampleSeat1 = new ShowSeat();
//        sampleSeat1.setStatus(ShowSeatStatus.AVAILABLE);
//        sampleSeat1.setLockedAt(new Date());
//        ShowSeat sampleSeat2 = new ShowSeat();
//        sampleSeat2.setStatus(ShowSeatStatus.AVAILABLE);
//        sampleSeat2.setLockedAt(new Date());
//        List<ShowSeat> showSeats = new ArrayList<>(List.of(sampleSeat1, sampleSeat2));
//        Mockito.when(showSeatRepository.findAllById(anyList())).thenReturn(showSeats);
//
//        List<ShowSeat> showSeatWithIds = new ArrayList<>();
//        int idx = 200;
//        for(ShowSeat showSeat : showSeats) {
//            ShowSeat showSeatWithId = new ShowSeat(showSeat);
//            showSeatWithId.setStatus(ShowSeatStatus.BLOCKED);
//            showSeatWithId.setId(idx);
//            idx++;
//            showSeatWithIds.add(showSeatWithId);
//        }
//        Mockito.when(showSeatRepository.save(showSeats)).thenReturn(showSeatWithIds);
//
//        Mockito.when(priceCalculationService.getTotalAmount(any(Show.class), anyList())).thenReturn(1000);
//
//        Booking savedBooking = new Booking();
//        savedBooking.setUser(user);
//        savedBooking.setShow(show);
//        savedBooking.setId(5000);
//        savedBooking.setTotalAmount(1000);
//        Mockito.when(bookingRepository.save(any(Booking.class))).thenReturn(savedBooking);
//
//        // Act
//        Booking resultBooking = bookingService.createBooking(1L, 1000L, List.of(10L, 11L));
//
//        // Assert
//        Assertions.assertEquals(resultBooking.getTotalAmount(), 1000);
//    }




    @Test
    void createBooking_v2() throws ShowSeatTypeNotFoundException, UserNotFoundException, SeatAlreadyBookedException, ShowNotFoundException {
        // Arrange
        User user = new User();
        Mockito.when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        Show show = new Show();
        Mockito.when(showRepository.findById(anyLong())).thenReturn(Optional.of(show));

        Mockito.when(priceCalculationService.getTotalAmount(any(Show.class), anyList())).thenReturn(1000);

        // Act
        Booking resultBooking = bookingService.createBooking(1L, 1000L, List.of(10L, 11L));

        // Assert
        Assertions.assertEquals(resultBooking.getTotalAmount(), 1000);
    }


}