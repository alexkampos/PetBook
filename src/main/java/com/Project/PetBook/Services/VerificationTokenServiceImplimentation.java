/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import com.Project.PetBook.Repos.VerificationTokenRepository;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Alkis
 */
@Service
public class VerificationTokenServiceImplimentation {

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenServiceImplimentation(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Transactional
    public VerificationToken findByToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Transactional
    public VerificationToken findByUser(MyUser myUser) {
        return verificationTokenRepository.findByMyUser(myUser);
    }

    public void save(MyUser myUser, String token) {
        VerificationToken verificationToken = new VerificationToken(token, myUser);
        //set expiry date to 24 hours
        verificationToken.setExpiryDate(calculateExpiryDate(24*60));
        
        verificationTokenRepository.save(verificationToken);

    }
    
    //calculateexpiry date
    
    private Timestamp calculateExpiryDate(int expiryTimeInMinutes){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE,expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    
    
    }
}
