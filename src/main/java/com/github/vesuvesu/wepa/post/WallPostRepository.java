package com.github.vesuvesu.wepa.post;

import com.github.vesuvesu.wepa.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WallPostRepository extends JpaRepository<WallPost, Long> {
    public WallPost findByWallOwnerAndId(User wallOwner, Long id);
}
