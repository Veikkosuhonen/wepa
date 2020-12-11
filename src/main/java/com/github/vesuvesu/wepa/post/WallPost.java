package com.github.vesuvesu.wepa.post;

import com.github.vesuvesu.wepa.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WallPost extends AbstractPersistable<Long> {

    private Date date;

    @Size(max=140)
    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    private User wallOwner;

    private int likes;

    @OneToMany
    private List<Comment> comments;

    public WallPost(String text, User author, User wallOwner) {
        this.text = text;
        this.author = author;
        this.wallOwner = wallOwner;
        date = new Date();
        likes = 0;
        comments = new ArrayList<>();
    }
}