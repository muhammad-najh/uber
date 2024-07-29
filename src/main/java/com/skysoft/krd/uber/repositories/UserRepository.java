package com.skysoft.krd.uber.repositories;

import com.skysoft.krd.uber.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long > {
    Optional<User> findByEmail(String email);
}
