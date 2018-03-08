package tarkvaratehnika.user;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value="/users/add", method=RequestMethod.POST,
            consumes = "application/json")
    public User addUser(@RequestBody User user) {
        System.out.println("user firstname= " + user.firstName);
        return userService.addUser(user);
    }

    @RequestMapping(value="/users", method=RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}