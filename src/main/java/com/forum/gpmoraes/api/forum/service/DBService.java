package com.forum.gpmoraes.api.forum.service;

import com.forum.gpmoraes.api.forum.model.Message;
import com.forum.gpmoraes.api.forum.model.Post;
import com.forum.gpmoraes.api.forum.model.User;
import com.forum.gpmoraes.api.forum.repositories.MessageRepository;
import com.forum.gpmoraes.api.forum.repositories.PostRepository;
import com.forum.gpmoraes.api.forum.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Service
public class DBService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    MessageRepository messageRepository;

    public void instantiateTestDatabase(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy@HH:mm");
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");

        User u1 = null;
        User u2 = null;

        Post p1 = null;
        Post p2 = null;

        Message m1 = null;
        Message m2 = null;
        try {
            u1 = new User(null,"Teste","user@teste.com","teste123","password123", sdf2.parse("22-05-1996"));
            u2 = new User(null,"Teste2","user2@teste.com","teste223","password223", sdf2.parse("10-02-1990"));

            p1 = new Post(null, sdf.parse("27-09-2017@09:23"),"first",u1);
            p2 = new Post(null,sdf.parse("29-09-2017@10:32"),"Testing second post",u2);

            m1 = new Message(null, sdf.parse("30-09-2017@10:15"),"Hello Test",null,u1,p1);
            m2 = new Message(null, sdf.parse("30-09-2017@10:32") ,"How are you ?",m1, u1,p1);


        } catch (ParseException e) {
            e.printStackTrace();
        }


        u1.getPosts().addAll(Arrays.asList(p1));
        u2.getPosts().addAll(Arrays.asList(p2));
        u1.getMessages().addAll(Arrays.asList(m1, m2));

        p1.getMessages().addAll(Arrays.asList(m1,m2));

        userRepository.saveAll(Arrays.asList(u1,u2));

        postRepository.saveAll(Arrays.asList(p1, p2));

        messageRepository.saveAll(Arrays.asList(m1,m2));

    }
}
