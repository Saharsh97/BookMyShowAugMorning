package org.example.bookmyshowaugmorning.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshowaugmorning.models.enums.BookingStatus;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    @ManyToOne
    private User user;

    @ManyToOne
    private Show show;

    private Date bookedAt;

    @OneToMany
    private List<ShowSeat> showSeats;
    private int totalAmount;

    @OneToMany
    private List<Payment> payments;

    @Enumerated
    private BookingStatus bookingStatus;
}
