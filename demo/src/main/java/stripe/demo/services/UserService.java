package stripe.demo.services;

import stripe.demo.dtos.UserDto;

public interface UserService {
    public UserDto addUser(UserDto userDto);
}
