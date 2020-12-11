package com.github.vesuvesu.wepa.post;

import com.github.vesuvesu.wepa.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Post extends AbstractPersistable<Long> {

    @OneToOne
    private ImageObject image;

    private Date date;

    private String caption;

    @ManyToOne
    private User author;

    private int likes;

    @OneToMany
    private List<Comment> comments;
}