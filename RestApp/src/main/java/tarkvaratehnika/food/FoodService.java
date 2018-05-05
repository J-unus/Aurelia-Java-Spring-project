package tarkvaratehnika.food;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class FoodService {

    private FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    public Food addFood(Food food) {
        foodRepository.save(food);
        return food;
    }

    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    public Food getFoodById(long foodId) {
        return foodRepository.findOne(foodId);
    }

    public Food updateFoodById(Food newFood, long foodId) {
        Food oldFood = foodRepository.findOne(foodId);
        oldFood.setDescription(newFood.getDescription());
        oldFood.setImage(newFood.getImage());
        oldFood.setPrice(newFood.getPrice());
        oldFood.setTitle(newFood.getTitle());
        oldFood.setModifiedTs(new Timestamp(System.currentTimeMillis()));
        foodRepository.save(oldFood);
        return oldFood;
    }

    public String deleteFoodById(long foodId) {
        foodRepository.delete(foodId);
        return "{\"deleted\": true}";
    }
}
