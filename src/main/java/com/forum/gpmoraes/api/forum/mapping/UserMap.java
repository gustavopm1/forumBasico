package com.forum.gpmoraes.api.forum.mapping;

import com.forum.gpmoraes.api.forum.dto.UserDTO;
import com.forum.gpmoraes.api.forum.model.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface UserMap {

    UserDTO convertToDto (User user);
    User convertFromDto (UserDTO userDTO);
}
