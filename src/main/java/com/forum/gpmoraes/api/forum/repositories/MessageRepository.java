package com.forum.gpmoraes.api.forum.repositories;

import com.forum.gpmoraes.api.forum.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {

}
