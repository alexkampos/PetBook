
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.Friendships;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Repos.FriendshipsRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FriendshipsServiceImplimentation implements FriendshipsServiceInterface {

    @Autowired
    FriendshipsRepo friendshipsRepo;

    @Override
    public void insertFrienship(Friendships friendships) {

        friendshipsRepo.save(friendships);
    }

    @Override
    public List<Friendships> getFriendshipList(MyUser currentUser) {
        return friendshipsRepo.findByFriendOneOrFriendTwo(currentUser);
    }

}
