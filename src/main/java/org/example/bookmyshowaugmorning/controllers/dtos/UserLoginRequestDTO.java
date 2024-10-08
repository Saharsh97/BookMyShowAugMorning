package org.example.bookmyshowaugmorning.controllers.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDTO {
    public String email;
    public String password;
}
