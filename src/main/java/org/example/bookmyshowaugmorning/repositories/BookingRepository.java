package org.example.bookmyshowaugmorning.repositories;


import org.example.bookmyshowaugmorning.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking save(Booking booking);
}
