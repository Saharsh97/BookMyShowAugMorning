package org.example.bookmyshowaugmorning.utils;

import org.example.bookmyshowaugmorning.exceptions.PasswordTooShortException;

public class UserUtils {

    public static void validatePassword(String password) throws PasswordTooShortException {
        if(password.length() < 6) {
            throw new PasswordTooShortException("Password must be at least 6 characters");
        }
    }
}
