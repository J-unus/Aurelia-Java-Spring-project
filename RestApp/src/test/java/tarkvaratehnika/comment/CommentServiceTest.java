package tarkvaratehnika.comment;

import org.junit.Before;
import org.junit.Test;
import tarkvaratehnika.food.Food;
import tarkvaratehnika.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class CommentServiceTest {
    private CommentRepository repository;
    private CommentService service;

    @Before
    public void setUp() {
        repository = mock(CommentRepository.class);
        service = new CommentService(repository);
    }

    @Test
    public void addComment() {
        User user = new User();
        Food food = new Food();
        List<Comment> comments = new ArrayList<>();

        user.setComments(comments);
        food.setComments(comments);

        service.addComment(new Comment(), user, food);
        verify(repository).findAll();
    }

    @Test
    public void getAllComments() {
        User user = new User();
        Food food = new Food();
        List<Comment> comments = new ArrayList<>();

        user.setComments(comments);
        food.setComments(comments);

        service.addComment(new Comment(), user, food);
        service.addComment(new Comment(), user, food);
        service.addComment(new Comment(), user, food);

        service.getAllComments();
        verify(repository, times(3)).findAll();
    }

    @Test
    public void getCommentById() {
        User user = new User();
        Food food = new Food();
        List<Comment> comments = new ArrayList<>();

        user.setComments(comments);
        food.setComments(comments);

        service.addComment(new Comment(), user, food);
        service.getCommentById(0L);
        verify(repository).findOne(0L);
    }
}
