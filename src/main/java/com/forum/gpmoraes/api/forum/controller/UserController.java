package com.forum.gpmoraes.api.forum.controller;

import com.forum.gpmoraes.api.forum.dto.UserDTO;
import com.forum.gpmoraes.api.forum.model.User;
import com.forum.gpmoraes.api.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> find(@PathVariable Integer id){
           User user =  userService.find(id);
           return ResponseEntity.ok().body(user);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> insert(@Valid @RequestBody UserDTO userDTO) {
        User user = userService.fromDTO(userDTO);
        user = userService.insert(user);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT )
    public ResponseEntity<Void> update(@Valid @RequestBody UserDTO userDTO, @PathVariable Integer id){
        User user = userService.fromDTO(userDTO);
        user.setId(id);
        user = userService.update(user);
        return ResponseEntity.noContent().build();
    }


    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
