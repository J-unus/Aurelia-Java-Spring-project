package tarkvaratehnika.food;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodController.class)
public class FoodControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FoodController foodController;

    @Test
    public void getAllFoods() throws Exception {
        Food food = new Food();
        food.setDescription("descritooom");

        List<Food> allFoods = Collections.singletonList(food);

        given(foodController.getAllFoods()).willReturn(allFoods);
        mvc.perform(get("/foods").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].description", is(food.getDescription())));
    }

    @Test
    public void getFood() throws Exception {
        Food food = new Food();
        food.setDescription("descriptioon");

        given(foodController.getFood(food.getId())).willReturn(food);
        mvc.perform(get("/foods/0").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description", is(food.getDescription())));
    }
}
