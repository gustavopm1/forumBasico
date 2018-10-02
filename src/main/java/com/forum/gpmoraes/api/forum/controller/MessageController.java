package com.forum.gpmoraes.api.forum.controller;

import com.forum.gpmoraes.api.forum.dto.MessageDTO;
import com.forum.gpmoraes.api.forum.model.Message;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.service.MessageService;
import com.forum.gpmoraes.api.forum.service.PostService;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<Message> find(@PathVariable Integer postId, @PathVariable Integer id) {

        Post post = postService.find(postId);
        Message message = messageService.find(id);

        if (post.getMessages().contains(message))
            return ResponseEntity.ok().body(message);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }


    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Integer postId) {

        Post post = postService.find(postId);

        Message message = messageService.fromDTO(messageDTO);
        message = messageService.insert(message);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}").buildAndExpand(message.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody MessageDTO messageDTO, @PathVariable Integer postId, @PathVariable Integer id) {
        try {

            Post post = postService.find(postId);

            Message message = messageService.fromDTO(messageDTO);

            messageService.update(message);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer postId, @PathVariable Integer id) {

        Post post = postService.find(postId);
        Message message = messageService.find(id);

        if (post.getMessages().contains(message)) {
            messageService.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
