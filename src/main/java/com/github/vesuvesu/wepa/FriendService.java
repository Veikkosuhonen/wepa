package com.github.vesuvesu.wepa;

import com.github.vesuvesu.wepa.friend.FriendRequest;
import com.github.vesuvesu.wepa.friend.FriendRequestRepository;
import com.github.vesuvesu.wepa.friend.FriendRequestStatus;
import com.github.vesuvesu.wepa.user.User;
import com.github.vesuvesu.wepa.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class FriendService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendRequestRepository friendRequestRepository;

    @Autowired
    private UserService userService;


    @Transactional
    public boolean sendRequest(String receiverName) {

        User sender = userService.getUser();

        User receiver = userRepository.findByName(receiverName);

        if (receiver == null || sender == null) {
            System.out.println("something went wrong when sending a request to " + receiverName);
            //Todo error message
            return false;
        }

        //Add request to receivers list if it contains none from the sender
        if (receiver.getIncomingFriendRequests().stream().noneMatch(r -> sender.equals(userRepository))) {

            FriendRequest request = new FriendRequest(sender, receiver, new Date(), FriendRequestStatus.PENDING);
            friendRequestRepository.save(request);

            sender.getSentFriendRequests().add(request);
            receiver.getIncomingFriendRequests().add(request);
        } else {
            System.out.println("request not added since " + receiverName + " already had a request from user");
            return false;
        }
        return true;
    }


    @Transactional
    public boolean resolveRequest(String senderName, String action) {
        User actor = userService.getUser();

        User sender = userRepository.findByName(senderName);
        if (sender==null) {
            System.out.println("Sender '"+senderName+"' of this friend request is null");
            //todo 404
            return false;
        }

        FriendRequest request = actor.getIncomingFriendRequests()
                .stream()
                .filter(r -> r.getSender().equals(sender))
                .findFirst().get();

        if (request != null) {

            if (action.equals("accept")) {
                actor.getIncomingFriendRequests().remove(request);

                sender.addFriend(actor);
                actor.addFriend(sender);
                //Todo send accept message to sender

                friendRequestRepository.delete(request);
                System.out.println(actor.getName() + " accepted friend request from " + sender.getName());

            } else if (action.equals("decline")) {
                actor.getIncomingFriendRequests().remove(request);
                request.setStatus(FriendRequestStatus.DECLINED);

                //Todo send decline message to sender
                friendRequestRepository.delete(request);
                System.out.println(actor.getName() + " declined friend request from " + sender.getName());

            } else {
                System.out.println("Action '"+action+"' was invalid");
                //todo 404
                return false;
            }
        } else {
            System.out.println("This friend request did not exist");
            //todo 404
            return false;
        }
        return true;
    }
}
