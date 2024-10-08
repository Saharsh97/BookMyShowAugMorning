package org.example.bookmyshowaugmorning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.bookmyshowaugmorning.models.*;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(long Id);
}
