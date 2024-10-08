package org.example.bookmyshowaugmorning.services;

import org.example.bookmyshowaugmorning.exceptions.ShowSeatTypeNotFoundException;
import org.example.bookmyshowaugmorning.models.*;
import org.example.bookmyshowaugmorning.repositories.ShowSeatTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriceCalculationService {

    private final ShowSeatTypeRepository showSeatTypeRepository;

    @Autowired
    public PriceCalculationService(ShowSeatTypeRepository showSeatTypeRepository) {
        this.showSeatTypeRepository = showSeatTypeRepository;
    }

    public int getTotalAmount(Show show, List<ShowSeat> showSeats) throws ShowSeatTypeNotFoundException {
        // 1. get the corresponding SeatTypes.
        int totalAmount = 0;
        for(ShowSeat showSeat : showSeats){
            Seat seat = showSeat.getSeat();
            SeatType seatType = seat.getSeatType();

            Optional<ShowSeatType> optionalShowSeatType = showSeatTypeRepository.findShowSeatTypeByShowAndSeatType(show, seatType);
            if(optionalShowSeatType.isEmpty()){
                throw new ShowSeatTypeNotFoundException("Show Seat Type doesn't exist");
            }
            ShowSeatType showSeatType = optionalShowSeatType.get();
            totalAmount += showSeatType.getPrice(); // base price
        }
        totalAmount += applyExtraCostForLateNightShow(show, totalAmount);
        totalAmount += addChargesForUrgentBooking(show, totalAmount);
        // n number of charges and so on...
        return totalAmount;
    }

    private int applyExtraCostForLateNightShow(Show show, int basePrice) {
        return 0;
    }

    private int addChargesForUrgentBooking(Show show, int basePrice) {
        return 0;
    }
}
