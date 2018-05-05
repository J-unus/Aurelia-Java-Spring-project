package tarkvaratehnika.food;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FoodServiceTest {
    private FoodRepository repository;
    private FoodService service;

    @Before
    public void setUp() {
        repository = mock(FoodRepository.class);
        service = new FoodService(repository);
    }

    @Test
    public void getAllFoods() {
        service.addFood(new Food());
        service.addFood(new Food());
        service.addFood(new Food());

        service.getAllFoods();
        verify(repository).findAll();
    }

    @Test
    public void getFoodById() {
        service.addFood(new Food());

        service.getFoodById(0L);
        verify(repository).findOne(0L);
    }
}
