package com.forum.gpmoraes.api.forum.controller;

import com.forum.gpmoraes.api.forum.dto.PostDTO;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{postId}")
    public ResponseEntity<Post> find(@PathVariable Integer postId) {
        Post post = postService.find(postId);
        return ResponseEntity.ok().body(post);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody PostDTO postDTO) {
        Post post = postService.fromDTO(postDTO);
        post = postService.insert(post);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{postId}").buildAndExpand(post.getPostId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{postId}")
    public ResponseEntity<Void> update(@Valid @RequestBody PostDTO postDTO, @PathVariable Integer id) {
        Post post = postService.fromDTO(postDTO);
        post.setPostId(id);
        post = postService.update(post);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{postId}")
    public ResponseEntity<Void> delete(@PathVariable Integer postId) {
        postService.delete(postId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<PostDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "postId") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction){
        Page<Post> list = postService.findPage(page, linesPerPage, orderBy, direction);
        Page<PostDTO> listDto = list.map(obj -> new PostDTO(obj));
        return ResponseEntity.ok().body(listDto);
    }


}
