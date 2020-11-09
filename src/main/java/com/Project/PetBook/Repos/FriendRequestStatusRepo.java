
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.FriendRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestStatusRepo extends JpaRepository<FriendRequestStatus, Integer>{
    
    public FriendRequestStatus findByStatusName (String statusName);
    
}
