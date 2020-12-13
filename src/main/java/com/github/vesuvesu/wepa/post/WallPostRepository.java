package com.github.vesuvesu.wepa.post;

import com.github.vesuvesu.wepa.user.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WallPostRepository extends JpaRepository<WallPost, Long> {
    public WallPost findByWallOwnerAndId(User wallOwner, Long id);
    public List<WallPost> findByWallOwner(User wallOwner, Pageable pageable);
}
