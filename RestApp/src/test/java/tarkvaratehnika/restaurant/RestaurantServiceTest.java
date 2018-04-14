package tarkvaratehnika.restaurant;

import org.junit.Before;
import org.junit.Test;
import tarkvaratehnika.foodCategory.FoodCategory;
import tarkvaratehnika.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class RestaurantServiceTest {

    private RestaurantRepository repository;
    private RestaurantService service;

    @Before
    public void setUp() {
        repository = mock(RestaurantRepository.class);
        service = new RestaurantService(repository);
    }

    @Test
    public void addRestaurant() {
        Restaurant restaurant = new Restaurant();
        service.addRestaurant(restaurant);
        verify(repository).save(restaurant);
    }

    @Test
    public void addFoodCategory() {
        Restaurant restaurant = new Restaurant();
        FoodCategory foodCategory1 = new FoodCategory();
        FoodCategory foodCategory2 = new FoodCategory();

        List<FoodCategory> foodCategories = new ArrayList<FoodCategory>();
        foodCategories.add(foodCategory1);
        restaurant.setFoodCategories(foodCategories);

        service.addFoodCategory(foodCategory2, restaurant);
        verify(repository).save(restaurant);
    }

    @Test
    public void getAllRestaurants() {
        User user = new User();

        Restaurant restaurant1 = new Restaurant();
        Restaurant restaurant2 = new Restaurant();
        Restaurant restaurant3 = new Restaurant();

        List<Restaurant> restaurants = new ArrayList<Restaurant>();
        restaurants.add(restaurant1);
        restaurants.add(restaurant2);
        restaurants.add(restaurant3);

        user.setRestaurants(restaurants);

        service.getAllRestaurants();
        verify(repository).findAll();

    }

    @Test
    public void getRestaurantById() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);

        service.addRestaurant(restaurant);
        service.getRestaurantById(1L);
        verify(repository).findOne(1L);
    }
}
