package stripe.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import stripe.demo.dtos.UserDto;
import stripe.demo.services.UserService;

@RestController
public class UserController {

    @Autowired


    UserService userService;

    @PostMapping("/add-user")
    public ResponseEntity<UserDto> addUser(
            @RequestBody UserDto userDto) {
        var result = userService.addUser(userDto);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
