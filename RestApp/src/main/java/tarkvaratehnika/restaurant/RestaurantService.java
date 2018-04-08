package tarkvaratehnika.restaurant;

import org.springframework.stereotype.Service;
import tarkvaratehnika.food.FoodService;
import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.foodCategory.FoodCategoryService;

import java.util.ArrayList;
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
    public Restaurant addRestaurantFoodCategory(FoodCategory foodCategory,long restaurantId){
        Restaurant restaurant = getRestaurantById(restaurantId);
        List foodCategoryList = restaurant.getFoodCategory();
        foodCategoryList.add(foodCategory);
        foodCategory.setRestaurant(restaurant);
        restaurant.setFoodCategory(foodCategoryList);
        return restaurantRepository.save(restaurant);
    }

    List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    Restaurant getRestaurantById(long restaurantId) {
        return restaurantRepository.findOne(restaurantId);
    }


}
