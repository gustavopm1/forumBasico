package com.forum.gpmoraes.api.forum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.gpmoraes.api.forum.dto.PostDTO;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.model.User;
import com.forum.gpmoraes.api.forum.service.PostService;
import com.forum.gpmoraes.api.forum.service.UserService;
import com.forum.gpmoraes.api.forum.service.exceptions.DataIntegrityException;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;


    @Before
    public void setUp() {

        User userFake = User.builder()
                .id(1)
                .user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com")
                .build();

        Post postFake = postFake = Post.builder()
                .postId(1)
                .user(userFake)
                .description("Testing")
                .build();

        when(userService.insert(any(User.class))).thenReturn(User.builder().id(22).user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com").build());

        when(postService.insert(any(Post.class))).thenReturn(Post.builder().postId(1).user(userFake)
                .description("Testing")
                .build());

        when(postService.update(any(Post.class))).thenReturn(Post.builder().postId(1).user(userFake)
                .description("Testing")
                .build());

    }


    @Test
    public void GetShouldReturnPostFromService() throws Exception {
        this.mockMvc.perform(get("/posts/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void PostShouldInsertPost() throws Exception {

        User userFake = User.builder()
                .id(1)
                .user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com")
                .build();

        Post postFake = postFake = Post.builder()
                .postId(1)
                .user(userFake)
                .description("Testing")
                .build();


        String json = new ObjectMapper().writeValueAsString(postFake);


        this.mockMvc.perform(post("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void DeleteShouldremovePost() throws Exception {

        User userFake = User.builder()
                .id(1)
                .user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com")
                .build();

        Post postFake = postFake = Post.builder()
                .postId(1)
                .user(userFake)
                .description("Testing")
                .build();

        this.mockMvc.perform(delete("/posts/1")).andDo(print()).andExpect(status().isNoContent());
    }


    @Test
    public void updateShouldUpdateUser() throws Exception {
        User userFake = User.builder()
                .id(1)
                .user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com")
                .build();

        PostDTO postFakeUpdated = PostDTO.builder()
                .postId(1)
                .user(userFake)
                .description("Testing Update")
                .build();

        String json = new ObjectMapper().writeValueAsString(postFakeUpdated);

        this.mockMvc.perform(put("/posts/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isNoContent());


    }

    @Test
    public void PostShouldInsertPost2() throws Exception {

        User userFake = User.builder()
                .id(1)
                .user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com")
                .build();

        Post postFake = postFake = Post.builder()
                .postId(1)
                .user(userFake)
                .description("Testing")
                .build();


        String json = new ObjectMapper().writeValueAsString(postFake);

        when(postService.insert(any(Post.class))).thenThrow(new DataIntegrityException("Teste"));

        this.mockMvc.perform(post("/posts")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().is(500));

    }

}


