package org.example.bookmyshowaugmorning.controllers;

import org.example.bookmyshowaugmorning.controllers.dtos.CreateBookingRequestDTO;
import org.example.bookmyshowaugmorning.controllers.dtos.CreateBookingResponseDTO;
import org.example.bookmyshowaugmorning.controllers.enums.ResponseStatus;
import org.example.bookmyshowaugmorning.exceptions.SeatAlreadyBookedException;
import org.example.bookmyshowaugmorning.exceptions.ShowNotFoundException;
import org.example.bookmyshowaugmorning.exceptions.ShowSeatTypeNotFoundException;
import org.example.bookmyshowaugmorning.exceptions.UserNotFoundException;
import org.example.bookmyshowaugmorning.models.Booking;
import org.example.bookmyshowaugmorning.services.BookingService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;

@SpringBootTest
public class BookingControllerTest {
    private BookingController bookingController;

    private BookingService bookingService;

    @Before
    public void setup(){
        bookingService = Mockito.mock(BookingService.class); // double
        bookingController = new BookingController(bookingService);
    }

    @After
    public void tearDown(){
//        bookingService = null;
//        bookingController = null;
    }

    @Test
    public void testBookingController_success() throws UserNotFoundException, SeatAlreadyBookedException, ShowNotFoundException, ShowSeatTypeNotFoundException {
        // Arrange
        CreateBookingRequestDTO requestDTO = new CreateBookingRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setShowId(50L);
        requestDTO.setShowSeatIds(List.of(10L, 11L));

        Booking sampleBooking = new Booking();
        sampleBooking.setId(123L);
        // when we call ProductService.createBooking,
        // we should return our own object,
        Mockito.when(bookingService.createBooking(anyLong(), anyLong(), anyList())).thenReturn(sampleBooking);

        // Act
        CreateBookingResponseDTO responseDTO = bookingController.createBooking(requestDTO);

        // Assert
        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(responseDTO.getStatus(), ResponseStatus.SUCCESS);
        Assertions.assertEquals(responseDTO.getBookingId(), 123L);
    }

    @Test
    public void testBookingController_failure() throws UserNotFoundException, SeatAlreadyBookedException, ShowNotFoundException, ShowSeatTypeNotFoundException {
        // Arrange
        CreateBookingRequestDTO requestDTO = new CreateBookingRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setShowId(50L);
        requestDTO.setShowSeatIds(List.of(10L, 11L));

        Mockito.when(bookingService.createBooking(anyLong(), anyLong(), anyList())).thenThrow(new RuntimeException());

        // Act
        CreateBookingResponseDTO responseDTO = bookingController.createBooking(requestDTO);

        // Assert
        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals(responseDTO.getStatus(), ResponseStatus.FAILURE);
        Assertions.assertNull(responseDTO.getBookingId());
    }

    @Test(expected = Exception.class)
    public void testBookingController_exception() throws UserNotFoundException, SeatAlreadyBookedException, ShowNotFoundException, ShowSeatTypeNotFoundException {
        // Arrange
        CreateBookingRequestDTO requestDTO = new CreateBookingRequestDTO();
        requestDTO.setUserId(1L);
        requestDTO.setShowId(50L);
        requestDTO.setShowSeatIds(List.of(10L, 11L));

        Mockito.when(bookingService.createBooking(anyLong(), anyLong(), anyList())).thenThrow(new RuntimeException());

        // Act
        bookingController.createBookingWithoutHandling(requestDTO);
    }
}
