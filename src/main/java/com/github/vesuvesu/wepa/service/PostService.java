package com.github.vesuvesu.wepa.service;

import com.github.vesuvesu.wepa.post.*;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ImageRepository imageRepository;

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

    @Transactional
    public boolean newPost(String caption, MultipartFile file) throws IOException {
        User user = userService.getUser();

        if (user.getPosts().size() >= 10) return false;

        ImageObject img = new ImageObject(file.getBytes());
        imageRepository.save(img);

        Post post = new Post(img, caption, user);
        postRepository.save(post);
        return true;
    }

    @Transactional
    public boolean removePost(String username, Long id) {
        User user = userService.getUser();
        if (!user.equals(userService.getUserByName(username))) return false;

        Post post = postRepository.getOne(id);
        postRepository.delete(post);
        return true;
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
