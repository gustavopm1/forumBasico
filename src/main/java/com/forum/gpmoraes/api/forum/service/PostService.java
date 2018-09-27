package com.forum.gpmoraes.api.forum.service;

import com.forum.gpmoraes.api.forum.dto.PostDTO;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.repositories.PostRepository;
import com.forum.gpmoraes.api.forum.service.exceptions.DataIntegrityException;
import com.forum.gpmoraes.api.forum.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post find (Integer id){
        return postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Post not found! Id: " + id + "."));
    }

    public Post insert (Post post){
        post.setPostId(null);
        return postRepository.save(post);
    }

    public Post update(Post post) {
        Post newPost = find(post.getPostId());
        updateData(newPost, post);
        return postRepository.save(newPost);
    }

    public void delete(Integer id){
        find(id);
        try{
            postRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Failed to delete!");
        }
    }

    public List<Post> findAll(){
        return postRepository.findAll();
    }

    public Page<Post> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return postRepository.findAll(pageRequest);
    }

    public Post fromDTO(PostDTO postDTO){
        return Post.builder()
                .postId(postDTO.getId())
                .date(postDTO.getDate())
                .description(postDTO.getDescription())
                .user(postDTO.getUser())
                .build();
    }

    private void updateData(Post newPost, Post post) {
        newPost.setDescription(post.getDescription());
    }
}
