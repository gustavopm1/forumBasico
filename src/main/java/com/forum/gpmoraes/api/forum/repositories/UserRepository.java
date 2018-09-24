package com.forum.gpmoraes.api.forum.repositories;

import com.forum.gpmoraes.api.forum.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
