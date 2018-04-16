package tarkvaratehnika.food;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    private FoodRepository foodRepository;

    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
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
        foodRepository.save(oldFood);
        return oldFood;
    }

    public String deleteFoodById(long foodId) {
        foodRepository.delete(foodId);
        return "{\"deleted\": true}";
    }
}
