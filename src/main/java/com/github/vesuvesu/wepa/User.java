package com.github.vesuvesu.wepa;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
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

    @ManyToMany
    private List<User> friendRequests;

    public User(String name) {
        this.name = name;
        this.friends = new ArrayList<>();
        this.friendRequests = new ArrayList<>();
    }
}