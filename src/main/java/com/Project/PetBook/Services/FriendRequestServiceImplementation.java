package com.Project.PetBook.Services;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.FriendRequestStatus;
import com.Project.PetBook.Models.Friendship;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Repos.FriendRequestRepo;
import com.Project.PetBook.Utils.UtilMethods;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestServiceImplementation implements FriendRequestService {

    @Autowired
    private FriendRequestRepo requestRepo;

    @Autowired
    private UtilMethods utilMethods;

    @Autowired
    private FriendRequestService requestInterface;

    @Autowired
    private MyUserService myUserServiceInterface;

    @Autowired
    private FriendRequestStatusService friendRequestStatusServiceInterface;

    @Autowired
    private FriendshipService friendshipsServiceInterface;

    @Override
    public List<FriendRequest> getSentFriendRequests() {
        return requestRepo.findBySenderId(utilMethods.getLoggedInUser());
    }

    @Override
    public List<FriendRequest> getReceivedFriendRequests() {
        return requestRepo.findByReceiverId(utilMethods.getLoggedInUser());
    }

    @Override
    public void insertFriendRequest(FriendRequest fr) {
        requestRepo.save(fr);
    }

    @Override
    public void removeFriendRequest(MyUser senderId, MyUser receiverId) {

        requestRepo.delete(requestRepo.findBySenderIdAndReceiverId(senderId, receiverId));
    }

    @Override
    public void sendRequest(int id) {

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();

        // Getting Friend Request Status "PENDING" 
        FriendRequestStatus statusId = friendRequestStatusServiceInterface.getFriendRequestStatusByName("PENDING");

        //Getting Sender User
        MyUser senderId = utilMethods.getLoggedInUser();

        //Getting Receiver User
        MyUser receiverId = myUserServiceInterface.getUserById(id);

        FriendRequest friendRequest = new FriendRequest(dt, statusId, senderId, receiverId);

        requestInterface.insertFriendRequest(friendRequest);
    }

    @Override
    public void acceptRequest(int id) {
        MyUser friendOne;
        MyUser friendTwo;

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();

        //Getting receiver
        MyUser currentUser = utilMethods.getLoggedInUser();

        //Getting sender User
        MyUser sender = myUserServiceInterface.getUserById(id);

        requestInterface.removeFriendRequest(sender, currentUser);

        if (currentUser.getUserId() < sender.getUserId()) {
            friendOne = currentUser;
            friendTwo = sender;
        } else {
            friendOne = sender;
            friendTwo = currentUser;
        }

        Friendship friendships = new Friendship(dt, friendOne, friendTwo);

        friendshipsServiceInterface.insertFrienship(friendships);

    }

    @Override
    public void rejectRequest(int id) {

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();

        //Getting receiver
        MyUser friendOne = utilMethods.getLoggedInUser();

        //Getting sender User
        MyUser friendTwo = myUserServiceInterface.getUserById(id);

        requestInterface.removeFriendRequest(friendTwo, friendOne);

        // Getting Friend Request Status "REJECTED" 
        FriendRequestStatus statusId = friendRequestStatusServiceInterface.getFriendRequestStatusByName("REJECTED");

        FriendRequest friendRequest = new FriendRequest(dt, statusId, friendTwo, friendOne);

        requestInterface.insertFriendRequest(friendRequest);
    }

}
