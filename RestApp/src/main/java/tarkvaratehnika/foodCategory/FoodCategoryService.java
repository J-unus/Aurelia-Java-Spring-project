package tarkvaratehnika.foodCategory;

import org.springframework.stereotype.Service;
import tarkvaratehnika.food.Food;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FoodCategoryService {

    private FoodCategoryRepository foodCategoryRepository;

    public FoodCategoryService(FoodCategoryRepository foodCategoryRepository) {
        this.foodCategoryRepository = foodCategoryRepository;
    }

    public FoodCategory addFoodCategory(FoodCategory foodCategory) {
        foodCategoryRepository.save(foodCategory);
        return foodCategory;
    }

    public Food addFood(Food food, FoodCategory foodCategory) {
        List<Food> foods = foodCategory.getFoods();
        foods.add(food);
        food.setFoodCategory(foodCategory);
        foodCategory.setFoods(foods);
        foodCategoryRepository.save(foodCategory);
        return food;
    }

    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryRepository.findAllByOrderByCategoryNameAsc();
    }

    public FoodCategory getFoodCategoryById(long foodCategoryId) {
        return foodCategoryRepository.findOne(foodCategoryId);
    }

    public FoodCategory updateFoodCategoryById(FoodCategory newFoodCategory, long foodCategoryId) {
        FoodCategory oldFoodCategory = foodCategoryRepository.findOne(foodCategoryId);
        oldFoodCategory.setCategoryName(newFoodCategory.getCategoryName());
        oldFoodCategory.setModifiedTs(new Timestamp(System.currentTimeMillis()));
        foodCategoryRepository.save(oldFoodCategory);
        return oldFoodCategory;
    }

    public String deleteFoodCategoryById(long foodCategoryId) {
        foodCategoryRepository.delete(foodCategoryId);
        return "{\"deleted\": true}";
    }
}
