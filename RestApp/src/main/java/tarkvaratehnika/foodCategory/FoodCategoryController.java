package tarkvaratehnika.foodCategory;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodCategoryController {

    private FoodCategoryService foodCategoryService;

    public FoodCategoryController(FoodCategoryService foodCategoryService) {
        this.foodCategoryService = foodCategoryService;
    }

    @RequestMapping(value="/foodCategories/add", method= RequestMethod.POST,
            consumes = "application/json")
    public FoodCategory addFoodCategory(@RequestBody FoodCategory foodCategory) {
        return foodCategoryService.addFoodCategory(foodCategory);
    }
    @RequestMapping(value="/foodCategories", method=RequestMethod.GET)
    public List<FoodCategory> getAllFoodCategories() {
        return foodCategoryService.getAllFoodCategories();
    }

}
