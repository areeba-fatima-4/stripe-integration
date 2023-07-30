package stripe.demo.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import stripe.demo.domain.models.User;
import stripe.demo.dtos.UserDto;
import stripe.demo.payment.gateways.StripeClient;
import stripe.demo.repositories.UserRepository;
import stripe.demo.services.UserService;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {

     @Autowired private StripeClient stripeClient;
     @Autowired private UserRepository userRepository;


    @Override
    public UserDto addUser(UserDto userDto) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        var customerId = stripeClient.createCustomer(user.getName(), user.getEmail());
        user.setCustomerId(customerId);

        var saved = userRepository.save(user);
        userDto.setId(saved.getId());
        return userDto;

    }
}
