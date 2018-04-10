package tarkvaratehnika.user;

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
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserController userController;

    @Test
    public void addUser() throws Exception {
        String jsonUser = new JSONObject()
                .put("userName", "name").toString();

        MockHttpServletResponse response = mvc.perform(
                post("/users/add").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonUser)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    public void getAllUsers() throws Exception {
        User user = new User();
        user.setUserName("name");

        List<User> allUsers = Collections.singletonList(user);

        given(userController.getAllUsers()).willReturn(allUsers);
        mvc.perform(get("/users").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].userName", is(user.getUserName())));
    }

    @Test
    public void getUser() throws Exception {
        User user = new User();
        user.setUserName("name");

        given(userController.getUser(user.getId())).willReturn(user);
        mvc.perform(get("/users/0").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("userName", is(user.getUserName())));
    }

    @Test
    public void addRestaurant() throws Exception {
        String jsonRestaurant = new JSONObject()
                .put("name", "name").toString();

        MockHttpServletResponse response = mvc.perform(
                post("/users/addRestaurant/0").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRestaurant)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }
}
