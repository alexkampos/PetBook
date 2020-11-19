package com.Project.PetBook.Services;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.FriendRequestStatus;
import com.Project.PetBook.Models.Friendships;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Repos.FriendRequestRepo;
import com.Project.PetBook.Utils.UtilMethods;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestServiceImplementation implements FriendRequestServiceInterface {

    @Autowired
    private FriendRequestRepo requestRepo;

    @Autowired
    private UtilMethods utilMethods;

    @Autowired
    private MyUserServiceInterface myUserServiceInterface;

    @Autowired
    private FriendRequestStatusServiceInterface friendRequestStatusServiceInterface;

    @Autowired
    private FriendshipsServiceInterface friendshipsServiceInterface;

    @Override
    public List<FriendRequest> getTheSentFriendRequests() {
        return requestRepo.findBySenderId(utilMethods.getLoggedInUser());
    }

    @Override
    public List<FriendRequest> getTheReceivedFriendRequests() {
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
    public void requestSend(int id) {

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();

        // Getting Friend Request Status "PENDING" 
        FriendRequestStatus statusId = friendRequestStatusServiceInterface.getFriendRequestStatusByName("PENDING");

        //Getting Sender User
        MyUser senderId = utilMethods.getLoggedInUser();

        //Getting Receiver User
        MyUser receiverId = myUserServiceInterface.getUserById(id);

        FriendRequest friendRequest = new FriendRequest(dt, statusId, senderId, receiverId);

        insertFriendRequest(friendRequest);
    }

    @Override
    public void requestAccepted(int id) {
        MyUser friendOne;
        MyUser friendTwo;

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();

        //Getting receiver
        MyUser currentUser = utilMethods.getLoggedInUser();

        //Getting sender User
        MyUser sender = myUserServiceInterface.getUserById(id);

        removeFriendRequest(sender, currentUser);

        if (currentUser.getUserId() < sender.getUserId()) {
            friendOne = currentUser;
            friendTwo = sender;
        } else {
            friendOne = sender;
            friendTwo = currentUser;
        }

        Friendships friendships = new Friendships(dt, friendOne, friendTwo);

        friendshipsServiceInterface.insertFrienship(friendships);

    }

    @Override
    public void requestRejected(int id) {

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();

        //Getting receiver
        MyUser friendOne = utilMethods.getLoggedInUser();

        //Getting sender User
        MyUser friendTwo = myUserServiceInterface.getUserById(id);

        removeFriendRequest(friendTwo, friendOne);

        // Getting Friend Request Status "REJECTED" 
        FriendRequestStatus statusId = friendRequestStatusServiceInterface.getFriendRequestStatusByName("REJECTED");

        FriendRequest friendRequest = new FriendRequest(dt, statusId, friendTwo, friendOne);

        insertFriendRequest(friendRequest);
    }

}
