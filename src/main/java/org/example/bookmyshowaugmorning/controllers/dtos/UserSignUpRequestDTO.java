package org.example.bookmyshowaugmorning.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignUpRequestDTO {
    String userName;
    String email;
    String password;
}
