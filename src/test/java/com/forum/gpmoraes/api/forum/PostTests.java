package com.forum.gpmoraes.api.forum;

import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.model.User;
import com.forum.gpmoraes.api.forum.repositories.PostRepository;
import com.forum.gpmoraes.api.forum.service.PostService;
import com.forum.gpmoraes.api.forum.service.exceptions.ObjectNotFoundException;
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
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PostTests {

    @Mock
    private PostRepository postRepository;

    @Spy
    @InjectMocks
    private PostService postService;

    @Test
    public void testGETPostByID(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        User userFake = null;

        Optional<Post> postFake = null;

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf2.parse("22-05-1996"))
                    .build();

            postFake = Optional.of(Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(postFake).when(postRepository).findById(any(Integer.class));

        Post post = postService.find(1);

        assertEquals(Long.valueOf(1), Long.valueOf(postFake.get().getPostId()));
    }

    @Test
    public void testPOSTPost(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        User userFake = null;

        Post postFake = null;

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

        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(postFake).when(postRepository).save(any(Post.class));

        Post post = postService.insert(postFake);

        verify(postRepository, times(1)).save(any());
    }

    @Test
    public void testPUTPost(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        User userFake = null;
        Optional<Post> postFake = null;
        Post postFakeUpdated = null;

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf2.parse("22-05-1996"))
                    .build();

            postFake = Optional.of(Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build());

            postFakeUpdated = Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing update")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(postFake).when(postRepository).findById(any(Integer.class));
        doReturn(postFakeUpdated).when(postRepository).save(any(Post.class));

        Post post = postService.update(postFakeUpdated);

        verify(postRepository, times(1)).save(any());

        assertEquals(Long.valueOf(1),Long.valueOf(post.getPostId()));
        assertEquals("Testing update",post.getDescription());
    }

    @Test
    public void testDELETEPost(){
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        User userFake = null;

        Optional<Post> postFake = null;

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf2.parse("22-05-1996"))
                    .build();

            postFake = Optional.of(Post.builder()
                    .postId(1)
                    .user(userFake)
                    .description("Testing")
                    .date(sdf.parse("20-05-2017@12:55"))
                    .build());

        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(postFake).when(postRepository).findById(any(Integer.class));

        postService.delete(1);
        verify(postRepository,times(1)).deleteById(1);
    }


}
