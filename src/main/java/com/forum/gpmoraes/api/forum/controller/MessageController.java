package com.forum.gpmoraes.api.forum.controller;

import com.forum.gpmoraes.api.forum.dto.MessageDTO;
import com.forum.gpmoraes.api.forum.mapping.MessageMap;
import com.forum.gpmoraes.api.forum.model.Message;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.service.MessageService;
import com.forum.gpmoraes.api.forum.service.PostService;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/posts/{postId}/messages")
public class MessageController {

    @Autowired
    MessageService messageService;

    @Autowired
    PostService postService;

    private MessageMap messageMap = Mappers.getMapper(MessageMap.class);

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> find(@PathVariable Integer postId, @PathVariable Integer id) {

        try {
            Post post = postService.find(postId);
            Message message = messageService.find(id);

            if (post.getMessages().contains(message))
                return ResponseEntity.ok().body(message);
            else
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Integer postId) {
    try {
        Post post = postService.find(postId);

        Message message = messageMap.convertFromDto(messageDTO);
        message = messageService.insert(message);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(message.getId()).toUri();
        return ResponseEntity.created(uri).build();
    } catch(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Integer postId, @PathVariable Integer id) {
        try {
            Post post = postService.find(postId);

            Message message = messageMap.convertFromDto(messageDTO);

            messageService.update(message);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer postId, @PathVariable Integer id) {
        try {
            Post post = postService.find(postId);
            Message message = messageService.find(id);

            if (post.getMessages().contains(message)) {
                messageService.delete(id);
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
