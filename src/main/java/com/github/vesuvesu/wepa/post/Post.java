package com.github.vesuvesu.wepa.post;

import com.github.vesuvesu.wepa.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Post extends AbstractPersistable<Long> {

    @OneToOne
    private ImageObject image;

    private Date date;

    private String caption;

    @ManyToOne
    private User user;
}
