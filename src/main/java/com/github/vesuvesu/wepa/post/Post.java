package com.github.vesuvesu.wepa.post;

import com.github.vesuvesu.wepa.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Post extends AbstractPersistable<Long> {

    @OneToOne
    @Basic(fetch = FetchType.LAZY)
    private ImageObject image;

    private Date date;

    @Size(max=140)
    private String caption;

    @ManyToOne
    private User author;

    private int likes;

    @OneToMany
    private List<Comment> comments;

    public Post(ImageObject img, String caption, User author) {
        this.image = img;
        this.caption = caption;
        this.author = author;
        date = new Date();
        likes = 0;
        comments = new ArrayList<>();
    }
}