package com.forum.gpmoraes.api.forum.repositories;

import com.forum.gpmoraes.api.forum.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

}
