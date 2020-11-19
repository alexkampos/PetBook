package com.Project.PetBook.Services;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.MyUser;
import java.util.List;

public interface FriendRequestServiceInterface {

    public List<FriendRequest> getTheSentFriendRequests();

    public List<FriendRequest> getTheReceivedFriendRequests();

    public void insertFriendRequest(FriendRequest friendRequest);

    public void removeFriendRequest(MyUser senderId, MyUser receiverId);
    
    public void requestSend(int id);
    
    public void requestAccepted(int id);
    
    public void requestRejected(int id);

}
