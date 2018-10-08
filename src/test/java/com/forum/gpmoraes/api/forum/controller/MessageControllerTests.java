package com.forum.gpmoraes.api.forum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.gpmoraes.api.forum.dto.MessageDTO;
import com.forum.gpmoraes.api.forum.model.Message;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.model.User;
import com.forum.gpmoraes.api.forum.service.MessageService;
import com.forum.gpmoraes.api.forum.service.PostService;
import com.forum.gpmoraes.api.forum.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(MessageController.class)
public class MessageControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MessageService messageService;

    @MockBean
    private PostService postService;

    @MockBean
    private UserService userService;

    @Before
    public void setUp(){
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

        Message messageFake = Message.builder()
                .id(1)
                .parentMessage(null)
                .messages(null)
                .post(postFake)
                .user(userFake)
                .text("Message Fake")
                .build();

        postFake.setMessages(Arrays.asList(messageFake));


        when(postService.find(any(Integer.class))).thenReturn(postFake);

        when(messageService.find(any(Integer.class))).thenReturn(messageFake);

        when(userService.insert(any(User.class))).thenReturn(userFake);

        when(postService.insert(any(Post.class))).thenReturn(postFake);

        when(messageService.insert(any(Message.class))).thenReturn(messageFake);

    }

    @Test
    public void shouldReturnMessageFromService() throws Exception{

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

        Message messageFake = Message.builder()
                .id(1)
                .parentMessage(null)
                .messages(null)
                .post(postFake)
                .user(userFake)
                .text("Message Fake")
                .build();

        postFake.setMessages(Arrays.asList(messageFake));

        this.mockMvc.perform(get("/posts/1/messages/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void postShouldInsertMessage() throws Exception{

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

        Message messageFake = Message.builder()
                .id(1)
                .parentMessage(null)
                .messages(null)
                .post(postFake)
                .user(userFake)
                .text("Message Fake")
                .build();



        String json = new ObjectMapper().writeValueAsString(messageFake);

        this.mockMvc.perform(post("/posts/1/messages")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteShouldRemoveMessage() throws Exception{
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

        Message messageFake = Message.builder()
                .id(1)
                .parentMessage(null)
                .messages(null)
                .post(postFake)
                .user(userFake)
                .text("Message Fake")
                .build();

        this.mockMvc.perform(delete("/posts/1/messages/1")).andDo(print()).andExpect(status().isNoContent());
    }

    @Test
    public void updateShouldUpdateMessage() throws Exception{

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

        MessageDTO messageFakeUpdated = MessageDTO.builder()
                .id(1)
                .parentMessage(null)
                .text("Message Fake")
                .build();

        String json = new ObjectMapper().writeValueAsString(messageFakeUpdated);

        this.mockMvc.perform(put("/posts/1/messages/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isNoContent());

    }
}