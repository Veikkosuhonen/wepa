package com.github.vesuvesu.wepa.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class ImageObject extends AbstractPersistable<Long> {

    @Lob
    private byte[] content;
}
