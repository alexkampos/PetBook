package com.Project.PetBook.Services;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.MyUser;
import java.util.List;

public interface FriendRequestServiceInterface {

    public List<FriendRequest> getSentFriendRequests();

    public List<FriendRequest> getReceivedFriendRequests();

    public void insertFriendRequest(FriendRequest friendRequest);

    public void removeFriendRequest(MyUser senderId, MyUser receiverId);

}
