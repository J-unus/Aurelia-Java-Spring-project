package tarkvaratehnika.foodCategory;

import org.springframework.web.bind.annotation.*;
import tarkvaratehnika.food.Food;

import java.util.List;

@RestController
public class FoodCategoryController {

    private FoodCategoryService foodCategoryService;

    public FoodCategoryController(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @RequestMapping(value="/foodCategories/add", method=RequestMethod.POST,
            consumes = "application/json")
    public FoodCategory addFoodCategory(@RequestBody FoodCategory foodCategory) {
        return foodCategoryService.addFoodCategory(foodCategory);
    }


    @RequestMapping(value="/foodCategories", method=RequestMethod.GET)
    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryService.getAllFoodCategories();
    }

    @RequestMapping(value = "/foodCategories/{id}", method=RequestMethod.GET)
    public FoodCategory getFoodCategory(@PathVariable("id") long foodCategoryId) {
        return foodCategoryService.getFoodCategoryById(foodCategoryId);
    }


    @RequestMapping(value = "/foodCategories/addFood/{id}", method=RequestMethod.POST,consumes = "application/json")
    public FoodCategory addFood(@RequestBody Food food, @PathVariable("id") long foodCategoryId) {
        return foodCategoryService.addFood(food, foodCategoryService.getFoodCategoryById(foodCategoryId));
    }
    /*
    @RequestMapping(value = "/restaurants/addFoodCategory/{id}", method=RequestMethod.POST,consumes = "application/json")
    public Restaurant addFoodCategory(@RequestBody FoodCategory foodCategory, @PathVariable("id") long restaurantId) {
        return restaurantService.addFoodCategory(foodCategory, restaurantService.getRestaurantById(restaurantId));
    }
     */
}
