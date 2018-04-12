package tarkvaratehnika.food;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FoodController {

    private FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }


    @RequestMapping(value="/foods", method=RequestMethod.GET)
    public List<Food> getAllFoods() {
        return foodService.getAllFoods();
    }


}
