package com.github.vesuvesu.wepa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String word);
    User findByName(String name);
}
