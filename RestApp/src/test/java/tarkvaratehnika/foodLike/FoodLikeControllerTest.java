package tarkvaratehnika.foodLike;

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
@WebMvcTest(FoodLikeController.class)
public class FoodLikeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private FoodLikeController foodLikeController;

    @Test
    public void addFoodLike() throws Exception {
        String jsonFoodLike = new JSONObject()
                .put("rating", false).toString();

        MockHttpServletResponse response = mvc.perform(
                post("/foodLikes/add?user_id=1&food_id=1").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFoodLike)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    public void getAllFoodLikes() throws Exception {
        FoodLike foodLike = new FoodLike();
        foodLike.setRating(false);

        List<FoodLike> allFoodLikes = Collections.singletonList(foodLike);

        given(foodLikeController.getAllFoodLikes()).willReturn(allFoodLikes);
        mvc.perform(get("/foodLikes").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rating", is(foodLike.isRating())));
    }

    @Test
    public void getFoodLike() throws Exception {
        FoodLike foodLike = new FoodLike();
        foodLike.setRating(false);

        given(foodLikeController.getFoodLike(foodLike.getId())).willReturn(foodLike);
        mvc.perform(get("/foodLikes/0").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("rating", is(foodLike.isRating())));
    }
}
