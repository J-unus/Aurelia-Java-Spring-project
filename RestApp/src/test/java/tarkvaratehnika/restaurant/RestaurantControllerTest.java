package tarkvaratehnika.restaurant;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantController restaurantController;

    @Test
    public void getAllRestaurants() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("name");

        List<Restaurant> allRestaurants = Collections.singletonList(restaurant);

        given(restaurantController.getAllRestaurants()).willReturn(allRestaurants);
        mvc.perform(get("/restaurants").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(restaurant.getName())));
    }

    @Test
    public void getRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName("name");

        given(restaurantController.getRestaurant(restaurant.getId())).willReturn(restaurant);
        mvc.perform(get("/restaurants/0").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(restaurant.getName())));
    }

    @Test
    public void addFoodCategory() throws Exception {
        String jsonFoodCategory = new JSONObject()
                .put("categoryName", "categoryName").toString();

        MockHttpServletResponse response = mvc.perform(
                post("/restaurants/addFoodCategory/0").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonFoodCategory)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    public void updateRestaurant() throws Exception {
        String jsonRestaurant = new JSONObject()
                .put("content", "lorem ipsum").toString();

        MockHttpServletResponse response = mvc.perform(
                put("/restaurants/1").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRestaurant)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    public void deleteRestaurant() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                delete("/restaurants/1")).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}
