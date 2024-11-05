package org.example.bookmyshowaugmorning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshowaugmorning.models.enums.ShowSeatStatus;

import java.util.Date;

// contains all the attributes for the combination of
// show and a seat.
// Mapping table.
@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;

    @ManyToOne
    private Seat seat;

    @Enumerated
    private ShowSeatStatus status;

    private Date lockedAt;  // help me in maintaining the Blocking status
    // and changing it back to available if needed.

    public ShowSeat(ShowSeat showSeat) {
        this.show = showSeat.getShow();
        this.seat = showSeat.getSeat();
        this.status = showSeat.getStatus();
        this.lockedAt = showSeat.getLockedAt();
    }

    public ShowSeat() {

    }
}
