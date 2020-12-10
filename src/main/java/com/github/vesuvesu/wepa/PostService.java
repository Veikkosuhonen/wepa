package com.github.vesuvesu.wepa;

import com.github.vesuvesu.wepa.post.Post;
import com.github.vesuvesu.wepa.post.PostRepository;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    /**
     * @param username of the author
     * @param id of the Post
     * @return Post
     */
    public Post getPost(String username, Long id) {
        return postRepository.findByAuthorAndId(
                userRepository.findByName(username),
                id
        );
    }

    /**
     * @param username of the author
     * @param id of the Post
     * @return if success
     */
    @Transactional
    public boolean likePost(String username, Long id) {
        User author = userRepository.findByName(username);
        Post post = postRepository.findByAuthorAndId(author, id);

        if (post == null) return false;

        User actor = userService.getUser();

        if (actor.getLikedPosts().contains(post.getId())) {
            actor.getLikedPosts().remove(post.getId());
            post.setLikes(post.getLikes() - 1);
            return true;
        }

        post.setLikes(post.getLikes() + 1);
        actor.getLikedPosts().add(post.getId());

        return true;
    }
}
