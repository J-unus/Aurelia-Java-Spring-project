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

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(restaurant1);

        user.setRestaurants(restaurants);
        service.addRestaurant(user, restaurant2);
        verify(repository).save(user);
    }

    @Test
    public void getAllUsers() {
        service.addUser(new User());
        service.addUser(new User());
        service.addUser(new User());

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
