/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.Friendships;
import com.Project.PetBook.Models.MyUser;
import java.util.List;

/**
 *
 * @author Alkis
 */
public interface FriendshipsServiceInterface {

    public void insertFrienship(Friendships friendships);
    
    public List<Friendships> getFriendshipList(MyUser currentUser);
    
   

}
