package com.forum.gpmoraes.api.forum.controller;

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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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


        when(userService.insert(any(User.class))).thenReturn(User.builder().id(1).user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com").build());

        when(postService.insert(any(Post.class))).thenReturn(Post.builder().postId(1).user(userFake)
                .description("Testing")
                .build());

        when(messageService.insert(any(Message.class))).thenReturn(Message.builder().id(1).parentMessage(null)
                .messages(null)
                .post(postFake)
                .user(userFake)
                .text("Message Fake")
                .build());

        postFake.setMessages(Arrays.asList(messageFake));
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
}