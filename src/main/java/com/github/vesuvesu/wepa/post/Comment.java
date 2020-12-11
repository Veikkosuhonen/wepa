package com.github.vesuvesu.wepa.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Comment extends AbstractPersistable<Long> {

    private String author;

    @Size(max=140)
    private String text;

    private Date date;
}
