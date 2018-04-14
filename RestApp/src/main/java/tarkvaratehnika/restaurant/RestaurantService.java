package tarkvaratehnika.restaurant;

import org.springframework.stereotype.Service;
import tarkvaratehnika.foodCategory.FoodCategory;

import java.util.List;

@Service
public class RestaurantService {

    private RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepository.save(restaurant);
    }

    public Restaurant addFoodCategory(FoodCategory foodCategory, Restaurant restaurant) {
        List<FoodCategory> foodCategories = restaurant.getFoodCategories();
        foodCategories.add(foodCategory);
        foodCategory.setRestaurant(restaurant);
        restaurant.setFoodCategories(foodCategories);
        return restaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(long restaurantId) {
        return restaurantRepository.findOne(restaurantId);
    }

    public Restaurant updateRestaurantById(Restaurant restaurant, long restaurantId) {
        return null;
    }

    public void deleteRestaurantById(long restaurantId) {
        restaurantRepository.delete(restaurantId);
    }
}
