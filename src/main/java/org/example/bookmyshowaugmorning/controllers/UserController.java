package org.example.bookmyshowaugmorning.controllers;

import org.example.bookmyshowaugmorning.controllers.dtos.UserLoginRequestDTO;
import org.example.bookmyshowaugmorning.controllers.dtos.UserLoginResponseDTO;
import org.example.bookmyshowaugmorning.controllers.dtos.UserSignUpRequestDTO;
import org.example.bookmyshowaugmorning.controllers.dtos.UserSignUpResponseDTO;
import org.example.bookmyshowaugmorning.controllers.enums.ResponseStatus;
import org.example.bookmyshowaugmorning.models.User;
import org.example.bookmyshowaugmorning.services.UserService;
import org.example.bookmyshowaugmorning.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public UserSignUpResponseDTO signup(@RequestBody UserSignUpRequestDTO requestDTO){
        try{

            UserUtils.validatePassword(requestDTO.getPassword());

            User user = userService.signup(
                                requestDTO.getUserName(),
                                requestDTO.getEmail(),
                                requestDTO.getPassword()
                        );

            UserSignUpResponseDTO responseDTO = new UserSignUpResponseDTO();
            responseDTO.setUserId(user.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("You have successfully registered on our website!");
            return responseDTO;

        } catch (Exception e){

            UserSignUpResponseDTO responseDTO = new UserSignUpResponseDTO();
            responseDTO.setUserId(null);
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(e.getMessage());
            return responseDTO;
        }
    }

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginRequestDTO requestDTO){
        try{
            User user = userService.login(
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );

            UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
            responseDTO.setUserId(user.getId());
            responseDTO.setResponseStatus(ResponseStatus.SUCCESS);
            responseDTO.setMessage("You have successfully registered on our website!");
            return responseDTO;

        } catch (Exception e){

            UserLoginResponseDTO responseDTO = new UserLoginResponseDTO();
            responseDTO.setUserId(null);
            responseDTO.setResponseStatus(ResponseStatus.FAILURE);
            responseDTO.setMessage(e.getMessage());
            return responseDTO;
        }
    }
}
