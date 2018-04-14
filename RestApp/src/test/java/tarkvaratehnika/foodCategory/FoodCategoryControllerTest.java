package tarkvaratehnika.foodCategory;

import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodCategoryController.class)
public class FoodCategoryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FoodCategoryController foodCategoryController;

    @Test
    public void addFoodCategory() throws Exception {
    }

    @Test
    public void getAllFoodCategories() throws Exception {
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setCategoryName("name");

        List<FoodCategory> allFoodCategory = Collections.singletonList(foodCategory);

        given(foodCategoryController.getAllFoodCategories()).willReturn(allFoodCategory);
        mvc.perform(get("/foodCategories").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].categoryName", is(foodCategory.getCategoryName())));
    }

    @Test
    public void getFoodCategory() throws Exception {
        FoodCategory foodCategory = new FoodCategory();
        foodCategory.setCategoryName("name");

        given(foodCategoryController.getFoodCategory(foodCategory.getId())).willReturn(foodCategory);
        mvc.perform(get("/foodCategories/0").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("categoryName", is(foodCategory.getCategoryName())));
    }

    @Test
    public void addFood() throws Exception {
        String jsonFood = new JSONObject()
                .put("description", "description").toString();

        MockHttpServletResponse response = mvc.perform(
                post("/foodCategories/addFood/0").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFood)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }
}
