/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.Friendships;
import com.Project.PetBook.Models.MyUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Alkis
 */
public interface FriendshipsRepo extends JpaRepository<Friendships, Integer> {

    @Query(
            value = "SELECT * FROM  friendships \n"
            + "where friend_one = ?1 \n"
            + "or friend_two= ?1",
            nativeQuery = true)
    public List<Friendships> findByFriendOneOrFriendTwo(MyUser currentUser);

}
