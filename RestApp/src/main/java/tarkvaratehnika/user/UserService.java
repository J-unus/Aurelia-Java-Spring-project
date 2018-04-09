package tarkvaratehnika.user;

import org.springframework.stereotype.Service;
import tarkvaratehnika.restaurant.Restaurant;

import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        // here you can do some validations etc before saving the user
        return userRepository.save(user);
    }

    User addRestaurant(User user, Restaurant restaurant) {
        // here you can do some validations etc before saving the restaurant
        List<Restaurant> restaurants = user.getRestaurants();
        restaurants.add(restaurant);
        restaurant.setUser(user);
        user.setRestaurants(restaurants);
        return userRepository.save(user);
    }

    List<User> getAllUsers() {
        return userRepository.findAll();
    }

    User getUserById(long userId) {
        return userRepository.findOne(userId);
    }
}
