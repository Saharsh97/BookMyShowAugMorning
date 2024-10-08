package org.example.bookmyshowaugmorning.controllers.dtos;

import lombok.Getter;
import lombok.Setter;
import org.example.bookmyshowaugmorning.controllers.enums.ResponseStatus;

@Getter
@Setter
public class UserSignUpResponseDTO {
    private Long userId;
    private ResponseStatus responseStatus;
    private String message;
}
