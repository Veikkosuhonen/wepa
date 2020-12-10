package com.github.vesuvesu.wepa.friend;

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
public class FriendRequest extends AbstractPersistable<Long> {

    private String senderName;

    private String receiverName;

    private Date creationDate;

    private FriendRequestStatus status;
}

