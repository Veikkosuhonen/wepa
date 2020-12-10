package com.github.vesuvesu.wepa.user;

import com.github.vesuvesu.wepa.friend.FriendRequest;
import com.github.vesuvesu.wepa.post.Post;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractPersistable<Long> {

    private String name;

    @ManyToMany
    private List<User> friends;

    @OneToMany
    private List<FriendRequest> sentFriendRequests;

    @OneToMany
    private List<FriendRequest> incomingFriendRequests;

    @OneToMany(mappedBy = "author")
    private List<Post> posts;

    private HashSet<Long> likedPosts;

    public User(String name) {
        this.name = name;
        this.friends = new ArrayList<>();
        this.sentFriendRequests = new ArrayList<>();
        this.incomingFriendRequests = new ArrayList<>();
        this.likedPosts = new HashSet<>();
    }

    public boolean addFriend(User friend) {
        if (this.friends.contains(friend)) {
            return false;
        }
        this.friends.add(friend);
        return true;
    }
}