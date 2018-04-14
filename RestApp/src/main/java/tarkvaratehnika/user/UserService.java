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
        return userRepository.save(user);
    }

    public User addRestaurant(User user, Restaurant restaurant) {
        List<Restaurant> restaurants = user.getRestaurants();
        restaurants.add(restaurant);
        restaurant.setUser(user);
        user.setRestaurants(restaurants);
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long userId) {
        return userRepository.findOne(userId);
    }
}
