
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.Friendship;
import com.Project.PetBook.Models.MyUser;
import java.util.List;

public interface FriendshipService {

    public void insertFrienship(Friendship friendships);
    
    public List<Friendship> getFriendshipList(MyUser currentUser);

}
