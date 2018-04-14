package tarkvaratehnika.foodLike;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tarkvaratehnika.food.FoodService;
import tarkvaratehnika.user.UserService;

import java.util.List;

@RestController
public class FoodLikeController {

    private UserService userService;
    private FoodService foodService;
    private FoodLikeService foodLikeService;

    public FoodLikeController(FoodLikeService foodLikeService, UserService userService, FoodService foodService) {
        this.foodLikeService = foodLikeService;
        this.userService = userService;
        this.foodService = foodService;
    }

    @RequestMapping(value = "/foodLikes/add", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public FoodLike addFoodLike(@RequestBody FoodLike foodLike,
                                @RequestParam(value = "user_id") long userId,
                                @RequestParam(value = "food_id") long foodId) {
        return foodLikeService.addFoodLike(foodLike, userService.getUserById(userId), foodService.getFoodById(foodId));
    }

    @RequestMapping(value = "/foodLikes", method = RequestMethod.GET)
    public List<FoodLike> getAllFoodLikes() {
        return foodLikeService.getAllFoodLikes();
    }

    @RequestMapping(value = "/foodLikes/{id}", method = RequestMethod.GET)
    public FoodLike getFoodLike(@PathVariable("id") long foodLikeId) {
        return foodLikeService.getFoodLikeById(foodLikeId);
    }
}
