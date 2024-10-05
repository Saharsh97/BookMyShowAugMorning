package org.example.bookmyshowaugmorning.repositories;


import org.example.bookmyshowaugmorning.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Booking save(Booking booking);
}
