package tarkvaratehnika.comment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tarkvaratehnika.food.FoodService;
import tarkvaratehnika.user.UserService;

import java.util.List;

@RestController
public class CommentController {

    private UserService userService;
    private FoodService foodService;
    private CommentService commentService;

    public CommentController(CommentService commentService, UserService userService, FoodService foodService) {
        this.commentService = commentService;
        this.userService = userService;
        this.foodService = foodService;
    }

    @RequestMapping(value="/comment/add", method= RequestMethod.POST,
            consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment addComment(@RequestBody Comment comment,
                                @RequestParam(value="user_id") long userId,
                                @RequestParam(value="food_id") long foodId) {
        return commentService.addComment(comment, userService.getUserById(userId), foodService.getFoodById(foodId));
    }

    @RequestMapping(value="/comments", method=RequestMethod.GET)
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @RequestMapping(value = "/comments/{id}", method=RequestMethod.GET)
    public Comment getComment(@PathVariable("id") long commentId) {
        return commentService.getCommentById(commentId);
    }
}
