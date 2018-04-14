package tarkvaratehnika.restaurantLike;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tarkvaratehnika.restaurant.RestaurantService;
import tarkvaratehnika.user.UserService;

import java.util.List;

@RestController
public class RestaurantLikeController {

    private UserService userService;
    private RestaurantService restaurantService;
    private RestaurantLikeService restaurantLikeService;

    public RestaurantLikeController(RestaurantLikeService restaurantLikeService, UserService userService,
                                    RestaurantService restaurantService) {
        this.restaurantLikeService = restaurantLikeService;
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    @RequestMapping(value = "/restaurantLike/add", method = RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public RestaurantLike addRestaurantLike(@RequestBody RestaurantLike restaurantLike,
                                            @RequestParam(value = "user_id") long userId,
                                            @RequestParam(value = "restaurant_id") long restaurantId) {
        return restaurantLikeService.addRestaurantLike(restaurantLike, userService.getUserById(userId),
                restaurantService.getRestaurantById(restaurantId));
    }

    @RequestMapping(value = "/restaurantLikes", method = RequestMethod.GET)
    public List<RestaurantLike> getAllRestaurantLikes() {
        return restaurantLikeService.getAllRestaurantLikes();
    }

    @RequestMapping(value = "/restaurantLikes/{id}", method = RequestMethod.GET)
    public RestaurantLike getRestaurantLike(@PathVariable("id") long restaurantLikeId) {
        return restaurantLikeService.getRestaurantLikeById(restaurantLikeId);
    }
}
