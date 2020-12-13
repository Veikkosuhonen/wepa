package com.github.vesuvesu.wepa.service;

import com.github.vesuvesu.wepa.post.Comment;
import com.github.vesuvesu.wepa.post.CommentRepository;
import com.github.vesuvesu.wepa.post.WallPost;
import com.github.vesuvesu.wepa.post.WallPostRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class WallService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    WallPostRepository wallPostRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;

    public List<WallPost> getWallPosts(User user) {
        Pageable pageable = PageRequest.of(0, 25, Sort.by("date").descending());
        return wallPostRepository.findByWallOwner(user, pageable);
    }

    public boolean wallPost(String text, String wallOwnerName) {
        User wallOwner = userRepository.findByName(wallOwnerName);
        User author = userService.getUser();

        if (wallOwner == null || author == null) return false;
        if (!author.canInteractWith(wallOwner)) return false;

        WallPost wallPost = new WallPost(text, author, wallOwner);
        wallPostRepository.save(wallPost);

        wallOwner.getWallPosts().add(wallPost);

        return true;
    }

    public boolean commentWallPost(String text, String wallOwnerName, Long id) {
        User author = userService.getUser();
        User wallOwner = userRepository.findByName(wallOwnerName);

        if (wallOwner == null || author == null) return false;
        if (!author.canInteractWith(wallOwner)) return false;

        WallPost wallPost = wallPostRepository.findByWallOwnerAndId(wallOwner, id);
        if (wallPost == null) return false;

        Comment comment = new Comment(author, text, new Date());
        commentRepository.save(comment);
        wallPost.getComments().add(comment);

        return true;
    }

    public boolean likeWallPost(String wallOwnerName, Long id) {
        User actor = userService.getUser();
        User wallOwner = userRepository.findByName(wallOwnerName);

        if (wallOwner == null || actor == null) return false;
        if (!actor.canInteractWith(wallOwner)) return false;

        WallPost wallPost = wallPostRepository.findByWallOwnerAndId(wallOwner, id);
        if (wallPost == null) return false;

        if (actor.getLikedPosts().contains(wallPost.getId())) {
            wallPost.setLikes(wallPost.getLikes() - 1);
            actor.getLikedPosts().remove(wallPost.getId());
        } else {
            wallPost.setLikes(wallPost.getLikes() + 1);
            actor.getLikedPosts().add(wallPost.getId());
        }

        return true;
    }
}
