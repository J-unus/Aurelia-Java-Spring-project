package tarkvaratehnika.user;

import java.util.List;

import org.springframework.stereotype.Service;
import tarkvaratehnika.restaurant.Restaurant;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        // here you can do some validations etc before saving the user
        Restaurant restaurant = new Restaurant();
        restaurant.setUser(user);
        user.setRestaurant(restaurant);
        return userRepository.save(user);
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

}