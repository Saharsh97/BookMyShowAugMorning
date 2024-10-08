package org.example.bookmyshowaugmorning.services;

import org.example.bookmyshowaugmorning.exceptions.PasswordIncorrectionException;
import org.example.bookmyshowaugmorning.exceptions.UserAlreadyExistsException;
import org.example.bookmyshowaugmorning.exceptions.UserNotFoundException;
import org.example.bookmyshowaugmorning.models.User;
import org.example.bookmyshowaugmorning.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(String userName, String email, String password) throws UserAlreadyExistsException {
        // 1. check if email already exists?
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isPresent()){
            throw new UserAlreadyExistsException("User with email " + email + " already exists!");
        }

        // 2. Now Im sure that user is not there in DB
        // make a new User object and save to our DB.
        User user = new User();
        user.setName(userName);
        user.setEmail(email);

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encodedPassword);

        User savedUser = userRepository.save(user);
        return savedUser;
    }

    public User login(String email, String password) throws UserNotFoundException, PasswordIncorrectionException {
        // 1.
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if(optionalUser.isEmpty()){
            throw new UserNotFoundException("User with email " + email + " not found!");
        }
        User user = optionalUser.get();


        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        Boolean isPasswordMatching = bCryptPasswordEncoder.matches(password, user.getPassword());
        if(!isPasswordMatching){
            throw new PasswordIncorrectionException("Invalid password!");
        }

        // login is successful!
        return user;
    }
}
