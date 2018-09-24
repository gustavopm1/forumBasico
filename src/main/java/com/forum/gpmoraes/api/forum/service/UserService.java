package com.forum.gpmoraes.api.forum.service;


import com.forum.gpmoraes.api.forum.dto.UserDTO;
import com.forum.gpmoraes.api.forum.model.User;
import com.forum.gpmoraes.api.forum.repositories.UserRepository;
import com.forum.gpmoraes.api.forum.service.exceptions.DataIntegrityException;
import com.forum.gpmoraes.api.forum.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User find (Integer id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException("User not found! Id: " + id + "."));
    }

    public User insert (User user){
        user.setId(null);
        return userRepository.save(user);
    }

    public User update(User user) {
        User newUser = find(user.getId());
        updateData(newUser, user);
        return userRepository.save(newUser);
    }

    public void delete(Integer id){
        find(id);
        try{
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException e){
            throw new DataIntegrityException("Failed to delete!");
        }
    }

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getId(), userDTO.getName(),userDTO.getEmail(),userDTO.getUser(),
                userDTO.getPassword(), userDTO.getBirthDate());
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setUser(user.getUser());
        newUser.setPassword(user.getPassword());
    }

}
