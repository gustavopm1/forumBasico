package com.forum.gpmoraes.api.forum.mapping;

import com.forum.gpmoraes.api.forum.dto.PostDTO;
import com.forum.gpmoraes.api.forum.model.Post;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PostMap {

    PostDTO convertToDto(Post post);

    @Mapping(source = "postId", target = "postId")
    Post convertFromDto (PostDTO postDTO);

}
