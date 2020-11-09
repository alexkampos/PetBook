
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.FriendRequestStatus;
import com.Project.PetBook.Repos.FriendRequestStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendRequestStatusServiceImplementation implements FriendRequestStatusServiceInterface {

    @Autowired
    private FriendRequestStatusRepo statusRepo;
    
    @Override
    public FriendRequestStatus getFriendRequestStatusByName(String statusName) {
        return statusRepo.findByStatusName(statusName);
    }
    
}
