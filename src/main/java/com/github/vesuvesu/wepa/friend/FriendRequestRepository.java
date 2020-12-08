package com.github.vesuvesu.wepa.friend;

import com.github.vesuvesu.wepa.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

    public List<FriendRequest> findByReceiver(User receiver);

    public List<FriendRequest> findBySender(User sender);
}
