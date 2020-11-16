/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Alkis
 */
@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer>{
    
    VerificationToken findByToken(String token);
    
    VerificationToken findByMyUser(MyUser myUser);
    
}
