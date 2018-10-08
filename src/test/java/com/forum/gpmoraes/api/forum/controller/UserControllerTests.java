package com.forum.gpmoraes.api.forum.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.forum.gpmoraes.api.forum.dto.UserDTO;
import com.forum.gpmoraes.api.forum.model.User;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Before
    public void setUp() {

        when(userService.insert(any(User.class))).thenReturn(User.builder().id(22).user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com").build());


        when(userService.update(any(User.class))).thenReturn(User.builder().id(1).user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com").build());

    }

    @Test
    public void getShouldReturnUserFromService() throws Exception {
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

        this.mockMvc.perform(get("/users/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void postShouldInsertUser() throws Exception {
        UserDTO userFake = UserDTO.builder()
                .id(22)
                .user("userFake")
                .password("passwordFake")
                .name("nameFake")
                .email("fake@fake.com")
                .build();


        String json = new ObjectMapper().writeValueAsString(userFake);

        System.out.println(json);

        this.mockMvc.perform(post("/users")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isCreated());

    }


    @Test
    public void deleteShouldRemoveUser() throws Exception {
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

        this.mockMvc.perform(delete("/users/1")).andDo(print()).andExpect(status().isNoContent());

    }

    @Test
    public void updateShouldUpdateUser() throws Exception {

        UserDTO userFakeUpdated =  UserDTO.builder()
                    .id(1)
                    .user("userFakeUpdated")
                    .password("passwordFake")
                    .name("nameFake")
                    .email("fake@fake.com")
                    .build();

        String json = new ObjectMapper().writeValueAsString(userFakeUpdated);

        this.mockMvc.perform(put("/users/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andDo(print())
                .andExpect(status().isNoContent());
    }


}
