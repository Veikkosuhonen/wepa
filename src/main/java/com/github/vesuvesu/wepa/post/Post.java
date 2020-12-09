package com.github.vesuvesu.wepa.post;

import com.github.vesuvesu.wepa.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Post extends AbstractPersistable<Long> {

    private byte[] image;

    private Date date;

    @ManyToOne
    private User user;
}
