package tarkvaratehnika.user;

import org.junit.Before;
import org.junit.Test;
import tarkvaratehnika.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserServiceTest {
    private UserRepository repository;
    private UserService service;

    @Before
    public void setUp() {
        repository = mock(UserRepository.class);
        service = new UserService(repository);
    }

    @Test
    public void addUser() {
        User user = new User();
        service.addUser(user);
        verify(repository).save(user);
    }

    @Test
    public void addRestaurant() {
        User user = new User();
        Restaurant restaurant1 = new Restaurant();
        Restaurant restaurant2 = new Restaurant();

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add(restaurant1);

        user.setRestaurants(restaurants);
        service.addRestaurant(user, restaurant2);
        verify(repository).save(user);
    }

    @Test
    public void getAllUsers() {
        User u1 = new User();
        u1.setId(1L);
        service.addUser(u1);

        User u2 = new User();
        u2.setId(2L);
        service.addUser(u2);

        User u3 = new User();
        u3.setId(3L);
        service.addUser(u3);

        service.getAllUsers();
        verify(repository).findAll();
    }

    @Test
    public void getUserById() {
        User user = new User();
        user.setId(1L);
        service.addUser(user);
        service.getUserById(1L);
        verify(repository).findOne(1L);
    }
}
