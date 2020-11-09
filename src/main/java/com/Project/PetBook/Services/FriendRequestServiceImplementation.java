
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.FriendRequest;
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
    
    @Override
    public List<FriendRequest> getSentFriendRequests() {
        return requestRepo.findBySenderId(utilMethods.getLoggedInUser());
    }

    @Override
    public List<FriendRequest> getReceivedFriendRequests() {             
        return requestRepo.findByReceiverId(utilMethods.getLoggedInUser());        
    }
    
    

}
