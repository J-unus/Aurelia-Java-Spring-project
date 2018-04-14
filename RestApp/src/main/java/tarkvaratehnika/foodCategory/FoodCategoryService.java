package tarkvaratehnika.foodCategory;

import org.springframework.stereotype.Service;
import tarkvaratehnika.food.Food;

import java.util.List;

@Service
public class FoodCategoryService {


    private FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public FoodCategory addFoodCategory(FoodCategory foodCategory) {
        // here you can do some validations etc before saving the user
        return foodCategoryRepository.save(foodCategory);
    }

    public FoodCategory addFood(Food food, FoodCategory foodCategory) {
        List<Food> foods = foodCategory.getFoods();
        foods.add(food);
        food.setFoodCategory(foodCategory);
        foodCategory.setFoods(foods);
        return foodCategoryRepository.save(foodCategory);
    }

    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryRepository.findAll();
    }

    public FoodCategory getFoodCategoryById(long foodCategoryId) {
        return foodCategoryRepository.findOne(foodCategoryId);
    }
}
