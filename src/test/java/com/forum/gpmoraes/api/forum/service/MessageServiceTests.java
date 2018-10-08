package com.forum.gpmoraes.api.forum.service;

import com.forum.gpmoraes.api.forum.model.Message;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.model.User;
import com.forum.gpmoraes.api.forum.repositories.MessageRepository;
import com.forum.gpmoraes.api.forum.repositories.PostRepository;
import com.forum.gpmoraes.api.forum.service.MessageService;
import com.forum.gpmoraes.api.forum.service.PostService;
import com.forum.gpmoraes.api.forum.service.exceptions.MessageNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MessageServiceTests {

    @Mock
    MessageRepository messageRepository;

    @Mock
    PostRepository postRepository;

    @Spy
    @InjectMocks
    MessageService messageService;

    @Spy
    @InjectMocks
    PostService postService;

    @Test
    public void testGETMessage(){
        Optional<Message> messageFake = null;

        Post postFake = null;

        User userFake = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf2.parse("22-05-1996"))
                    .build();

            postFake = Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build();

            messageFake = Optional.of(Message.builder()
                    .id(1)
                    .date(sdf.parse("20-05-2017@13:45"))
                    .parentMessage(null)
                    .messages(null)
                    .post(postFake)
                    .user(userFake)
                    .text("Message Fake")
                    .build());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(messageFake).when(messageRepository).findById(any(Integer.class));

        Message message = messageService.find(1);

        assertEquals(Long.valueOf(1), Long.valueOf(messageFake.get().getId()));

    }

    @Test
    public void testPOSTMessage(){
        Message messageFake = null;

        Post postFake = null;

        User userFake = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf2.parse("22-05-1996"))
                    .build();

            postFake = Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build();

            messageFake =Message.builder()
                    .id(1)
                    .date(sdf.parse("20-05-2017@13:45"))
                    .parentMessage(null)
                    .messages(null)
                    .post(postFake)
                    .user(userFake)
                    .text("Message Fake")
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(messageFake).when(messageRepository).save(any(Message.class));

        Message message = messageService.insert(messageFake);

        verify(messageRepository,times(1)).save(any());

    }

    @Test
    public void testPUTMessage(){
        Optional<Message> messageFake = null;
        Message messageFakeUpdated = null;

        Post postFake = null;

        User userFake = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf2.parse("22-05-1996"))
                    .build();

            postFake = Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build();

            messageFake = Optional.of(Message.builder()
                    .id(1)
                    .date(sdf.parse("20-05-2017@13:45"))
                    .parentMessage(null)
                    .messages(null)
                    .post(postFake)
                    .user(userFake)
                    .text("Message Fake")
                    .build());

            messageFakeUpdated =Message.builder()
                    .id(1)
                    .date(sdf.parse("20-05-2017@13:45"))
                    .parentMessage(null)
                    .messages(null)
                    .post(postFake)
                    .user(userFake)
                    .text("Message Fake Updated")
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(messageFake).when(messageRepository).findById(any(Integer.class));
        doReturn(messageFakeUpdated).when(messageRepository).save(any(Message.class));

        Message message = messageService.update(messageFakeUpdated);

        verify(messageRepository, times(1)).save(any());

        assertEquals(Long.valueOf(1),Long.valueOf(message.getId()));
        assertEquals("Message Fake Updated",message.getText());
    }

    @Test
    public void testDELETEMessage(){
        Optional<Message> messageFake = null;

        Post postFake = null;

        User userFake = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf2.parse("22-05-1996"))
                    .build();

            postFake = Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build();

            messageFake = Optional.of(Message.builder()
                    .id(1)
                    .date(sdf.parse("20-05-2017@13:45"))
                    .parentMessage(null)
                    .messages(null)
                    .post(postFake)
                    .user(userFake)
                    .text("Message Fake")
                    .build());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(messageFake).when(messageRepository).findById(any(Integer.class));

        messageService.delete(1);
        verify(messageRepository,times(1)).deleteById(1);
    }

    @Test(expected = MessageNotFoundException.class)
    public void testIsMessageDeletedAfterPostIsDeleted(){
        Optional<Message> messageFake = null;

        Post postFake = null;

        User userFake = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf2.parse("22-05-1996"))
                    .build();

            postFake = Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build();

            messageFake = Optional.of(Message.builder()
                    .id(1)
                    .date(sdf.parse("20-05-2017@13:45"))
                    .parentMessage(null)
                    .messages(null)
                    .post(postFake)
                    .user(userFake)
                    .text("Message Fake")
                    .build());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(Optional.ofNullable(postFake)).when(postRepository).findById(any(Integer.class));

        postService.delete(1);
        verify(postRepository,times(1)).deleteById(1);


        messageService.find(1);

    }
}
