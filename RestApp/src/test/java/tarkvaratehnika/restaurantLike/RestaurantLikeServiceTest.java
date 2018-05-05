package tarkvaratehnika.restaurantLike;

import org.junit.Before;
import org.junit.Test;
import tarkvaratehnika.restaurant.Restaurant;
import tarkvaratehnika.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class RestaurantLikeServiceTest {
    private RestaurantLikeRepository repository;
    private RestaurantLikeService service;

    @Before
    public void setUp() {
        repository = mock(RestaurantLikeRepository.class);
        service = new RestaurantLikeService(repository);
    }

    @Test
    public void addRestaurantLike() {
        User user = new User();
        Restaurant restaurant = new Restaurant();
        List<RestaurantLike> restaurantLikes = new ArrayList<>();

        user.setRestaurantLikes(restaurantLikes);
        restaurant.setRestaurantLikes(restaurantLikes);

        service.addRestaurantLike(new RestaurantLike(), user, restaurant);
        verify(repository).findAll();
    }

    @Test
    public void getAllRestaurantLikes() {
        User user = new User();
        User user2 = new User();
        Restaurant restaurant = new Restaurant();
        Restaurant restaurant2 = new Restaurant();
        List<RestaurantLike> restaurantLikes = new ArrayList<>();

        user.setRestaurantLikes(restaurantLikes);
        user2.setRestaurantLikes(restaurantLikes);
        restaurant.setRestaurantLikes(restaurantLikes);
        restaurant2.setRestaurantLikes(restaurantLikes);

        service.addRestaurantLike(new RestaurantLike(), user, restaurant);
        service.addRestaurantLike(new RestaurantLike(), user, restaurant2);
        service.addRestaurantLike(new RestaurantLike(), user2, restaurant);

        service.getAllRestaurantLikes();
        verify(repository, times(4)).findAll();
    }

    @Test
    public void getRestaurantLikeById() {
        User user = new User();
        Restaurant restaurant = new Restaurant();
        List<RestaurantLike> restaurantLikes = new ArrayList<>();

        user.setRestaurantLikes(restaurantLikes);
        restaurant.setRestaurantLikes(restaurantLikes);

        service.addRestaurantLike(new RestaurantLike(), user, restaurant);
        service.getRestaurantLikeById(0L);
        verify(repository).findOne(0L);
    }
}
