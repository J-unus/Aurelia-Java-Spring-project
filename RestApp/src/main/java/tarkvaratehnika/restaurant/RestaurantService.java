package tarkvaratehnika.restaurant;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {


    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        // here you can do some validations etc before saving the user
        return restaurantRepository.save(restaurant);
    }

    List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }
}
