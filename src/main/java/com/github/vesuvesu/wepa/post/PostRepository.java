package com.github.vesuvesu.wepa.post;

import com.github.vesuvesu.wepa.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    public Post findByAuthorAndId(User author, Long id);
    public List<Post> findByAuthor(User author);
}