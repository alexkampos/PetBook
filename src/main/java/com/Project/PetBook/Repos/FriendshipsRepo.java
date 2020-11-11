/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.Friendships;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Alkis
 */
public interface FriendshipsRepo extends JpaRepository<Friendships, Integer>{
    
    
}
