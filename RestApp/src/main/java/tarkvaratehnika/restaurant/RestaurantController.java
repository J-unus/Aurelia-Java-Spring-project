package tarkvaratehnika.restaurant;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestaurantController {

    private RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @RequestMapping(value="/restaurants/add", method= RequestMethod.POST,
            consumes = "application/json")
    public Restaurant addRestaurant(@RequestBody Restaurant restaurant) {
        return restaurantService.addRestaurant(restaurant);
    }
    @RequestMapping(value="/restaurants", method=RequestMethod.GET)
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }


}
