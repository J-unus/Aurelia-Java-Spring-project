package tarkvaratehnika.food;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @RequestMapping(value = "/foods", method = RequestMethod.GET)
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }

    @RequestMapping(value = "/foods/{id}", method = RequestMethod.GET)
    public Food getFood(@PathVariable("id") long foodId) {
        return foodService.getFoodById(foodId);
    }

    @RequestMapping(value = "/foods/{id}", method = RequestMethod.PUT,
            consumes = "application/json")
    public Food updateFood(@RequestBody Food food, @PathVariable("id") long foodId) {
        return foodService.updateFoodById(food, foodId);
    }

    @RequestMapping(value = "/foods/{id}", method = RequestMethod.DELETE)
    public void deleteFood(@PathVariable("id") long foodId) {
        foodService.deleteFoodById(foodId);
    }
}
