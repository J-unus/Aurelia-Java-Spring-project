package tarkvaratehnika.comment;

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
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentController commentController;

    @Test
    public void addComment() throws Exception {
        String jsonComment = new JSONObject()
                .put("content", "lorem ipsum").toString();

        MockHttpServletResponse response = mvc.perform(
                post("/comments/add?user_id=1&food_id=1").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonComment)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.CREATED.value());
    }

    @Test
    public void getAllComments() throws Exception {
        Comment comment = new Comment();
        comment.setContent("kiren kipsum");

        List<Comment> allComments = Collections.singletonList(comment);

        given(commentController.getAllComments()).willReturn(allComments);
        mvc.perform(get("/comments").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].content", is(comment.getContent())));
    }

    @Test
    public void getComment() throws Exception {
        Comment comment = new Comment();
        comment.setContent("lorem ipsum");

        given(commentController.getComment(comment.getId())).willReturn(comment);
        mvc.perform(get("/comments/0").accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content", is(comment.getContent())));
    }

    @Test
    public void updateComment() throws Exception {
        String jsonComment = new JSONObject()
                .put("content", "lorem ipsum").toString();

        MockHttpServletResponse response = mvc.perform(
                put("/comments/1").contentType(MediaType.APPLICATION_JSON)
                        .content(jsonComment)).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }

    @Test
    public void deleteComment() throws Exception {
        MockHttpServletResponse response = mvc.perform(
                delete("/comments/1")).andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.OK.value());
    }
}
