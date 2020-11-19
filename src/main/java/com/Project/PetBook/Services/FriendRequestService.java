package com.Project.PetBook.Services;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.MyUser;
import java.util.List;

public interface FriendRequestService {

    public List<FriendRequest> getTheSentFriendRequests();

    public List<FriendRequest> getTheReceivedFriendRequests();

    public void insertFriendRequest(FriendRequest friendRequest);

    public void removeFriendRequest(MyUser senderId, MyUser receiverId);
    
    public void sendRequest(int id);
    
    public void acceptRequest(int id);
    
    public void rejectRequest(int id);

}
