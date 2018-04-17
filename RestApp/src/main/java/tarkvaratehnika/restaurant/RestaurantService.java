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
        restaurantRepository.save(restaurant);
        return restaurant;
    }

    public FoodCategory addFoodCategory(FoodCategory foodCategory, Restaurant restaurant) {
        List<FoodCategory> foodCategories = restaurant.getFoodCategories();
        foodCategories.add(foodCategory);
        foodCategory.setRestaurant(restaurant);
        restaurant.setFoodCategories(foodCategories);
        restaurantRepository.save(restaurant);
        return foodCategory;
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public Restaurant getRestaurantById(long restaurantId) {
        return restaurantRepository.findOne(restaurantId);
    }

    public Restaurant updateRestaurantById(Restaurant newRestaurant, long restaurantId) {
        Restaurant oldRestaurant = restaurantRepository.findOne(restaurantId);
        oldRestaurant.setName(newRestaurant.getName());
        oldRestaurant.setOpenTime(newRestaurant.getOpenTime());
        oldRestaurant.setCloseTime(newRestaurant.getCloseTime());
        oldRestaurant.setPhoneNumber(newRestaurant.getPhoneNumber());
        oldRestaurant.setLocation(newRestaurant.getLocation());
        oldRestaurant.setDescription(newRestaurant.getDescription());
        restaurantRepository.save(oldRestaurant);
        return oldRestaurant;
    }

    public String deleteRestaurantById(long restaurantId) {
        restaurantRepository.delete(restaurantId);
        return "{\"deleted\": true}";
    }
}
