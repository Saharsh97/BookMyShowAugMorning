package org.example.bookmyshowaugmorning.repositories;

import org.example.bookmyshowaugmorning.models.SeatType;
import org.example.bookmyshowaugmorning.models.Show;
import org.example.bookmyshowaugmorning.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
    Optional<ShowSeatType> findShowSeatTypeByShowAndSeatType(Show show, SeatType seatType);
}
