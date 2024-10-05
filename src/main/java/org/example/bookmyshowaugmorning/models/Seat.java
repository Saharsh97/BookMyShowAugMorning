package org.example.bookmyshowaugmorning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "seats")
public class Seat extends BaseModel{
    private int seatNumber;

    @ManyToOne
    private SeatType seatType;

    private int rowNumber;
    private int columnNumber;
}
