package tarkvaratehnika.restaurant;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tarkvaratehnika.foodCategory.FoodCategory;

import java.util.List;

@RestController
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping(value = "/restaurants", method = RequestMethod.GET)
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }

    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.GET)
    public Restaurant getRestaurant(@PathVariable("id") long restaurantId) {
        return restaurantService.getRestaurantById(restaurantId);
    }

    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.PUT,
            consumes = "application/json")
    public Restaurant updateRestaurant(@RequestBody Restaurant restaurant,
                                       @PathVariable("id") long restaurantId) {
        return restaurantService.updateRestaurantById(restaurant, restaurantId);
    }

    @RequestMapping(value = "/restaurants/{id}", method = RequestMethod.DELETE)
    public String deleteRestaurant(@PathVariable("id") long restaurantId) {
        return restaurantService.deleteRestaurantById(restaurantId);
    }

    @RequestMapping(value = "/restaurants/addFoodCategory/{id}", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public FoodCategory addFoodCategory(@RequestBody FoodCategory foodCategory,
                                      @PathVariable("id") long restaurantId) {
        return restaurantService.addFoodCategory(foodCategory, restaurantService.getRestaurantById(restaurantId));
    }
}
