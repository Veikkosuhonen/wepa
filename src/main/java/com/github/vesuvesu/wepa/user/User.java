package com.github.vesuvesu.wepa.user;

import com.github.vesuvesu.wepa.friend.FriendRequest;
import com.github.vesuvesu.wepa.post.ImageObject;
import com.github.vesuvesu.wepa.post.Post;
import com.github.vesuvesu.wepa.post.WallPost;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "AppUser")
public class User extends AbstractPersistable<Long> {

    private String name;

    @ManyToMany
    private List<User> friends;

    @OneToMany(mappedBy = "sender")
    private List<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "receiver")
    private List<FriendRequest> incomingFriendRequests;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    @OneToMany(mappedBy = "author")
    private List<Post> authoredWallPosts;

    @OneToMany(mappedBy = "wallOwner")
    private List<WallPost> wallPosts;

    private HashSet<Long> likedPosts;

    @OneToOne
    private ImageObject profilePic;

    private boolean hasProfilePic;

    public User(String name) {
        this.name = name;
        this.friends = new ArrayList<>();
        this.sentFriendRequests = new ArrayList<>();
        this.incomingFriendRequests = new ArrayList<>();
        this.posts = new ArrayList<>();
        this.wallPosts = new ArrayList<>();
        this.authoredWallPosts = new ArrayList<>();
        this.likedPosts = new HashSet<>();
        hasProfilePic = false;
    }

    public boolean addFriend(User friend) {
        if (this.friends.contains(friend) && this.equals(friend)) {
            return false;
        }
        this.friends.add(friend);
        return true;
    }

    public boolean canInteractWith(User user) {
        if (friends.contains(user) || user.equals(this)) return true;
        else return false;
    }
}