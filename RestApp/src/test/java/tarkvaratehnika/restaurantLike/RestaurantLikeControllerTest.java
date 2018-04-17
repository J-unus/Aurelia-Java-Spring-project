package tarkvaratehnika.restaurantLike;

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
@WebMvcTest(RestaurantLikeController.class)
public class RestaurantLikeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantLikeController restaurantLikeController;

    @Test
    public void addRestaurantLike() throws Exception {
        String jsonRestaurantLike = new JSONObject()
                .put("rating", false).toString();

        MockHttpServletResponse response = mvc.perform(
                post("/restaurantLikes/add?user_id=1&restaurant_id=1").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRestaurantLike)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    public void getAllRestaurantLikes() throws Exception {
        RestaurantLike restaurantLike = new RestaurantLike();
        restaurantLike.setRating(false);

        List<RestaurantLike> allRestaurantLikes = Collections.singletonList(restaurantLike);

        given(restaurantLikeController.getAllRestaurantLikes()).willReturn(allRestaurantLikes);
        mvc.perform(get("/restaurantLikes").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].rating", is(restaurantLike.isRating())));
    }

    @Test
    public void getRestaurantLike() throws Exception {
        RestaurantLike restaurantLike = new RestaurantLike();
        restaurantLike.setRating(false);

        given(restaurantLikeController.getRestaurantLike(restaurantLike.getId())).willReturn(restaurantLike);
        mvc.perform(get("/restaurantLikes/0").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("rating", is(restaurantLike.isRating())));
    }
}
