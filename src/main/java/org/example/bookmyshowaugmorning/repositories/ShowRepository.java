package org.example.bookmyshowaugmorning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.bookmyshowaugmorning.models.Show;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {

    Optional<Show> findById(Long showId);
}
