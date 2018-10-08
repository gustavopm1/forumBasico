package com.forum.gpmoraes.api.forum.controller;

import com.forum.gpmoraes.api.forum.dto.PostDTO;
import com.forum.gpmoraes.api.forum.mapping.PostMap;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.service.PostService;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@Slf4j
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    private PostMap postMap = Mappers.getMapper(PostMap.class);

    @GetMapping(value = "/{postId}")
    public ResponseEntity<?> find(@PathVariable Integer postId) {
        try {
            Post post = postService.find(postId);
            return ResponseEntity.ok().body(post);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> insert(@Valid @RequestBody PostDTO postDTO) {
        try {
            Post post = postMap.convertFromDto(postDTO);
            post = postService.insert(post);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{postId}").buildAndExpand(post.getPostId()).toUri();
            return ResponseEntity.created(uri).build();
        } catch (Exception e) {
            log.error("Erro ao salvar Post!", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{postId}")
    public ResponseEntity<?> update(@Valid @RequestBody PostDTO postDTO, @PathVariable(name = "postId") Integer id) {
        try {
            Post post = postMap.convertFromDto(postDTO);
            post.setPostId(id);
            post = postService.update(post);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{postId}")
    public ResponseEntity<?> delete(@PathVariable Integer postId) {
        try {
            postService.delete(postId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "10") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "postId") String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC") String direction) {
        try {
            Page<Post> list = postService.findPage(page, linesPerPage, orderBy, direction);
            Page<PostDTO> listDto = list.map(obj -> new PostDTO(obj));
            return ResponseEntity.ok().body(listDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
