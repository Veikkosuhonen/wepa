package com.github.vesuvesu.wepa.user;

import com.github.vesuvesu.wepa.friend.FriendRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbstractPersistable<Long> {

    private String name;

    @ManyToMany
    private List<User> friends;

    @OneToMany(mappedBy = "sender")
    private List<FriendRequest> sentFriendRequests;

    @OneToMany(mappedBy = "receiver")
    private List<FriendRequest> incomingFriendRequests;

    public User(String name) {
        this.name = name;
        this.friends = new ArrayList<>();
        this.sentFriendRequests = new ArrayList<>();
        this.incomingFriendRequests = new ArrayList<>();
    }
}