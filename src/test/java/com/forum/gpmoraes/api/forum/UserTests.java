package com.forum.gpmoraes.api.forum;

import com.forum.gpmoraes.api.forum.model.User;
import com.forum.gpmoraes.api.forum.repositories.UserRepository;
import com.forum.gpmoraes.api.forum.service.UserService;
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
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserTests {

    @Mock
    UserRepository userRepository;

    @Spy
    @InjectMocks
    UserService userService;



    @Test
    public void testGETUser(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Optional<User> userFake = null;

        try {
            userFake = Optional.of(User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf.parse("22-05-1996"))
                    .build());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(userFake).when(userRepository).findById(any(Integer.class));

        User user = userService.find(1);

        assertEquals(Long.valueOf(1), Long.valueOf(userFake.get().getId()));
    }

    @Test
    public void testPOSTUser(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        User userFake = null;

        try {
            userFake = User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf.parse("22-05-1996"))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(userFake).when(userRepository).save(any(User.class));

        User user = userService.insert(userFake);

        verify(userRepository, times(1)).save(any());
    }

    @Test
    public void testPUTUser(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Optional<User> userFake = null;
        User userFakeUpdated = null;

        try {
            userFake = Optional.of(User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf.parse("22-05-1996"))
                    .build());

            userFakeUpdated = User.builder()
                    .id(1)
                    .user("userFakeUpdated")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf.parse("22-05-1996"))
                    .build();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(userFake).when(userRepository).findById(any(Integer.class));
        doReturn(userFakeUpdated).when(userRepository).save(any(User.class));

        User user = userService.update(userFakeUpdated);

        verify(userRepository, times(1)).save(any());

        assertEquals(Long.valueOf(1),Long.valueOf(user.getId()));
        assertEquals("userFakeUpdated",user.getUser());

    }

    @Test
    public void testDELETEUser(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        Optional<User> userFake = null;

        try {
            userFake = Optional.of(User.builder()
                    .id(1)
                    .user("userFake")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .birthDate(sdf.parse("22-05-1996"))
                    .build());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doReturn(userFake).when(userRepository).findById(any(Integer.class));

        userService.delete(1);
        verify(userRepository,times(1)).deleteById(1);
    }
}
