package tarkvaratehnika.foodCategory;

import org.springframework.web.bind.annotation.*;
import tarkvaratehnika.restaurant.Restaurant;
import tarkvaratehnika.restaurant.RestaurantService;

import java.util.List;

@RestController
public class FoodCategoryController {

    private FoodCategoryService foodCategoryService;

    public FoodCategoryController(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @RequestMapping(value="/foodCategories/add/{id}", method= RequestMethod.POST,
            consumes = "application/json")
    public FoodCategory addFoodCategory(@RequestBody FoodCategory foodCategory,@PathVariable("id") long restaurantId) {
        return foodCategoryService.addFoodCategory(foodCategory,restaurantId);
    }
    @RequestMapping(value="/foodCategories", method=RequestMethod.GET)
    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryService.getAllFoodCategories();
    }

}
