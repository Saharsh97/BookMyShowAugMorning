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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/book")
    public CreateBookingResponseDTO createBooking(@RequestBody CreateBookingRequestDTO requestDTO){
        CreateBookingResponseDTO responseDTO = new CreateBookingResponseDTO();
        try {
            Booking booking = bookingService.createBooking(
                                                    requestDTO.getUserId(),
                                                    requestDTO.getShowId(),
                                                    requestDTO.getShowSeatIds()
                                            );

            responseDTO.setBookingId(booking.getId());
            responseDTO.setStatus(ResponseStatus.SUCCESS);

        } catch (Exception e) {
            responseDTO.setBookingId(null);
            responseDTO.setStatus(ResponseStatus.FAILURE);
        }
        return responseDTO;
    }


    @PostMapping("/bookWithoutHandling")
    public CreateBookingResponseDTO createBookingWithoutHandling(@RequestBody CreateBookingRequestDTO requestDTO) throws UserNotFoundException, SeatAlreadyBookedException, ShowNotFoundException, ShowSeatTypeNotFoundException {
        CreateBookingResponseDTO responseDTO = new CreateBookingResponseDTO();
        Booking booking = bookingService.createBooking(
                requestDTO.getUserId(),
                requestDTO.getShowId(),
                requestDTO.getShowSeatIds()
        );
        responseDTO.setBookingId(booking.getId());
        responseDTO.setStatus(ResponseStatus.SUCCESS);
        return responseDTO;
    }
}
