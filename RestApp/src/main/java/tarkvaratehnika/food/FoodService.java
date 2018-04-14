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
}
