package tarkvaratehnika.foodCategory;

import org.springframework.stereotype.Service;
import tarkvaratehnika.restaurant.Restaurant;
import tarkvaratehnika.restaurant.RestaurantController;
import tarkvaratehnika.restaurant.RestaurantRepository;
import tarkvaratehnika.restaurant.RestaurantService;

import java.util.List;

@Service
public class FoodCategoryService {

    private FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public FoodCategory addFoodCategory(FoodCategory foodCategory,long restaurantId) {
        // here you can do some validations etc before saving the user
        return foodCategoryRepository.save(foodCategory);
    }

    List<FoodCategory> getAllFoodCategories() {
        return foodCategoryRepository.findAll();
    }

}
