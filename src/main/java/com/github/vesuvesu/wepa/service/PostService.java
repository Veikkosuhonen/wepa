package com.github.vesuvesu.wepa.service;

import com.github.vesuvesu.wepa.post.Comment;
import com.github.vesuvesu.wepa.post.CommentRepository;
import com.github.vesuvesu.wepa.post.Post;
import com.github.vesuvesu.wepa.post.PostRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * @param authorName
     * @param id of the Post
     * @return Post
     */
    public Post getPost(String authorName, Long id) {
        return postRepository.findByAuthorAndId(
                userRepository.findByName(authorName),
                id
        );
    }

    /**
     * @param authorName
     * @param id of the Post
     * @return if success
     */
    @Transactional
    public boolean likePost(String authorName, Long id) {
        User author = userRepository.findByName(authorName);
        Post post = postRepository.findByAuthorAndId(author, id);

        if (post == null) return false;

        User actor = userService.getUser();
        if (!actor.canInteractWith(author)) return false;

        if (actor.getLikedPosts().contains(post.getId())) {
            actor.getLikedPosts().remove(post.getId());
            post.setLikes(post.getLikes() - 1);
            return true;
        }

        post.setLikes(post.getLikes() + 1);
        actor.getLikedPosts().add(post.getId());

        return true;
    }

    @Transactional
    public boolean comment(String authorName, String text, Long id) {
        User author = userRepository.findByName(authorName);
        Post post = postRepository.findByAuthorAndId(author, id);

        if (post == null) return false;

        User actor = userService.getUser();
        if (actor == null) return false;
        if (!actor.canInteractWith(author)) return false;

        Comment comment = new Comment(actor, text, new Date());
        commentRepository.save(comment);
        post.getComments().add(comment);

        return true;
    }
}
