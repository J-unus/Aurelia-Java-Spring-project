package tarkvaratehnika.user;

import org.springframework.web.bind.annotation.*;
import tarkvaratehnika.restaurant.Restaurant;

import java.util.List;


@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/users/add", method=RequestMethod.POST,
            consumes = "application/json")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/users/{id}", method=RequestMethod.GET)
    public User getUser(@PathVariable("id") long userId) {
        return userService.getUserById(userId);
    }

    @RequestMapping(value = "/users/addRestaurant/{id}", method=RequestMethod.POST,consumes = "application/json")
    public User addRestaurant(@RequestBody Restaurant restaurant, @PathVariable("id") long userId) {
        return userService.addRestaurant(userService.getUserById(userId), restaurant);
    }


}