package com.forum.gpmoraes.api.forum.mapping;

import com.forum.gpmoraes.api.forum.dto.MessageDTO;
import com.forum.gpmoraes.api.forum.model.Message;
import org.mapstruct.Mapper;

@Mapper
public interface MessageMap {
    MessageDTO convertToDto(Message message);
    Message convertFromDto (MessageDTO messageDTO);
}
