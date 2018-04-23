package tarkvaratehnika.restaurantLike;

import org.junit.Before;
import org.junit.Test;
import tarkvaratehnika.restaurant.Restaurant;
import tarkvaratehnika.user.User;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

        RestaurantLike restaurantLike = new RestaurantLike();
        User user = new User();
        user.setId(1L);
        Restaurant restaurant = new Restaurant();
        user.setRestaurantLikes(new ArrayList<>());

        service.addRestaurantLike(restaurantLike, user, restaurant);

        service.getAllRestaurantLikes();
        verify(repository).findAll();
    }

    @Test
    public void getAllRestaurantLikes() {
        /*
        RestaurantLike u1 = new RestaurantLike();
        u1.setId(1L);
        service.addRestaurantLike(u1);

        RestaurantLike u2 = new RestaurantLike();
        u2.setId(2L);
        service.addRestaurantLike(u2);

        RestaurantLike u3 = new RestaurantLike();
        u3.setId(3L);
        service.addRestaurantLike(u3);

        service.getAllRestaurantLikes();
        verify(repository).findAll();
        */
    }

    @Test
    public void getRestaurantLikeById() {
        /*
        RestaurantLike restaurantLike = new RestaurantLike();
        restaurantLike.setId(1L);
        service.addRestaurantLike(restaurantLike);
        service.getRestaurantLikeById(1L);
        verify(repository).findOne(1L);
        */
    }
}
