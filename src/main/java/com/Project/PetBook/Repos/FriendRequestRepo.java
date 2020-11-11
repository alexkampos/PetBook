
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.MyUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRequestRepo extends JpaRepository<FriendRequest, Integer> {
    
    public List<FriendRequest> findBySenderId (MyUser senderId);
    
    public List<FriendRequest> findByReceiverId (MyUser receiverId);
    
    
}
