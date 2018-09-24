package com.forum.gpmoraes.api.forum.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @JsonFormat(pattern="dd-MM-yyyy@HH:mm")
    private Date date;
    private String description;

    private User user;

    public PostDTO(Post post){
        id = post.getPostId();
        date = post.getDate();
        description = post.getDescription();
        user = post.getUser();
    }

}
