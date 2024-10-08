package org.example.bookmyshowaugmorning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.bookmyshowaugmorning.models.ShowSeat;

import java.util.List;

@Repository
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    List<ShowSeat> findAllById(Iterable<Long> showSeatIDS);

    List<ShowSeat> save(List<ShowSeat> showSeats);
}
