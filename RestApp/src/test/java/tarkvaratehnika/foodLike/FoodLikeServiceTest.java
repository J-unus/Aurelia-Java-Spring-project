package tarkvaratehnika.foodLike;

import org.junit.Before;
import org.junit.Test;
import tarkvaratehnika.food.Food;
import tarkvaratehnika.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class FoodLikeServiceTest {
    private FoodLikeRepository repository;
    private FoodLikeService service;

    @Before
    public void setUp() {
        repository = mock(FoodLikeRepository.class);
        service = new FoodLikeService(repository);
    }

    @Test
    public void addFoodLike() {
        User user = new User();
        Food food = new Food();
        List<FoodLike> foodLikes = new ArrayList<>();

        user.setFoodLikes(foodLikes);
        food.setFoodLikes(foodLikes);

        service.addFoodLike(new FoodLike(), user, food);
        verify(repository).findAll();
    }

    @Test
    public void getAllFoodLikes() {
        User user = new User();
        User user2 = new User();
        Food food = new Food();
        Food food2 = new Food();
        List<FoodLike> foodLikes = new ArrayList<>();

        user.setFoodLikes(foodLikes);
        user2.setFoodLikes(foodLikes);
        food.setFoodLikes(foodLikes);
        food2.setFoodLikes(foodLikes);

        service.addFoodLike(new FoodLike(), user, food);
        service.addFoodLike(new FoodLike(), user, food2);
        service.addFoodLike(new FoodLike(), user2, food);

        service.getAllFoodLikes();
        verify(repository, times(4)).findAll();
    }

    @Test
    public void getFoodLikeById() {
        User user = new User();
        Food food = new Food();
        List<FoodLike> foodLikes = new ArrayList<>();

        user.setFoodLikes(foodLikes);
        food.setFoodLikes(foodLikes);

        service.addFoodLike(new FoodLike(), user, food);
        service.getFoodLikeById(0L);
        verify(repository).findOne(0L);
    }
}
