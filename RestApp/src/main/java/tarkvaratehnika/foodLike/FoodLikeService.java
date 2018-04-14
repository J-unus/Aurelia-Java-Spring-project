package tarkvaratehnika.foodLike;

import org.springframework.stereotype.Service;
import tarkvaratehnika.food.Food;
import tarkvaratehnika.user.User;

import java.util.List;

@Service
public class FoodLikeService {

    private FoodLikeRepository foodLikeRepository;

    public FoodLikeService(FoodLikeRepository foodLikeRepository) {
        this.foodLikeRepository = foodLikeRepository;
    }

    public FoodLike addFoodLike(FoodLike foodLike, User user, Food food) {
        List<FoodLike> userLikeList = user.getFoodLikes();
        List<FoodLike> foodLikeList = food.getFoodLikes();
        List<FoodLike> repoFoodLikes = foodLikeRepository.findAll();
        boolean flag = false;
        for (int i = 0; i < repoFoodLikes.size(); i++) {
            FoodLike repoFoodLike = repoFoodLikes.get(i);
            if (repoFoodLike.getUser() == user && repoFoodLike.getFood() == food) {
                foodLikeRepository.delete(repoFoodLike);
                if (foodLike.isRating() != repoFoodLike.isRating()) {
                    userLikeList.add(foodLike);
                    foodLikeList.add(foodLike);
                    foodLike.setUser(user);
                    foodLike.setFood(food);
                    return foodLikeRepository.save(foodLike);
                }
                flag = true;
                break;
            }
        }
        if (!flag) {
            userLikeList.add(foodLike);
            foodLikeList.add(foodLike);
            foodLike.setUser(user);
            foodLike.setFood(food);
            return foodLikeRepository.save(foodLike);
        }
        return null;
    }

    List<FoodLike> getAllFoodLikes() {
        return foodLikeRepository.findAll();
    }

    FoodLike getFoodLikeById(long foodLikeId) {
        return foodLikeRepository.findOne(foodLikeId);
    }
}
