package tarkvaratehnika.foodCategory;

import org.junit.Before;
import org.junit.Test;
import tarkvaratehnika.food.Food;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class FoodCategoryServiceTest {

    private FoodCategoryRepository repository;
    private FoodCategoryService service;

    @Before
    public void setUp() {
        repository = mock(FoodCategoryRepository.class);
        service = new FoodCategoryService(repository);
    }

    @Test
    public void addFoodCategory() {
        FoodCategory foodCategory = new FoodCategory();
        service.addFoodCategory(foodCategory);
        verify(repository).save(foodCategory);
    }

    @Test
    public void addFood() {
        FoodCategory foodCategory = new FoodCategory();
        Food food1 = new Food();
        Food food2 = new Food();

        List<Food> foods = new ArrayList<>();
        foods.add(food1);

        foodCategory.setFoods(foods);

        service.addFood(food2, foodCategory);
        verify(repository).save(foodCategory);
    }

    @Test
    public void getAllFoodCategories() {
        FoodCategory foodCategory1 = new FoodCategory();
        service.addFoodCategory(foodCategory1);

        FoodCategory foodCategory2 = new FoodCategory();
        service.addFoodCategory(foodCategory2);

        FoodCategory foodCategory3 = new FoodCategory();
        service.addFoodCategory(foodCategory3);

        service.getAllFoodCategories();
        verify(repository).findAllByOrderByCategoryNameAsc();
    }

    @Test
    public void getFoodCategoryById() {
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setId(1L);
        service.addFoodCategory(foodCategory);
        service.getFoodCategoryById(1L);
        verify(repository).findOne(1L);
    }
}
