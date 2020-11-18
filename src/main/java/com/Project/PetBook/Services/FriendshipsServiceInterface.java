
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.Friendships;
import com.Project.PetBook.Models.MyUser;
import java.util.List;



public interface FriendshipsServiceInterface {

    public void insertFrienship(Friendships friendships);
    
    public List<Friendships> getFriendshipList(MyUser currentUser);
    
   

}
