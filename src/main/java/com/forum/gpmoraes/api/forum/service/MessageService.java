package com.forum.gpmoraes.api.forum.service;

import com.forum.gpmoraes.api.forum.dto.MessageDTO;
import com.forum.gpmoraes.api.forum.model.Message;
import com.forum.gpmoraes.api.forum.repositories.MessageRepository;
import com.forum.gpmoraes.api.forum.service.exceptions.DataIntegrityException;
import com.forum.gpmoraes.api.forum.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Message find(Integer messageId){
        Optional<Message> message = messageRepository.findById(messageId);
        return message.orElseThrow(() -> new ObjectNotFoundException("Message not found! Id: " + messageId + "."));
    }

    public Message insert (Message message){
        message.setId(null);
        return messageRepository.save(message);
    }

    public Message update(Message post) {
        Message newMessage = find(post.getId());
        updateData(newMessage, post);
        return messageRepository.save(newMessage);
    }

    public void delete(Integer id){
        find(id);
        try{
            messageRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Failed to delete!");
        }
    }

    public Message fromDTO(MessageDTO messageDTO){
        return Message.builder().id(messageDTO.getId()).date(messageDTO.getDate()).text(messageDTO.getText()).parentMessage(messageDTO.getParentMessage())
                .user(messageDTO.getUser()).post(messageDTO.getPost()).build();
    }

    private void updateData(Message newMessage, Message message) {
        newMessage.setText(message.getText());
    }
}
