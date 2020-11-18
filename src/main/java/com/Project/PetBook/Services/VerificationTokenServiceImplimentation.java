package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import com.Project.PetBook.Repos.VerificationTokenRepository;
import java.sql.Timestamp;
import java.util.Calendar;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VerificationTokenServiceImplimentation implements VerificationTokenInterface {

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenServiceImplimentation(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    @Override
    @Transactional
    public VerificationToken findToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public VerificationToken findTokenByUser(MyUser myUser) {
        return verificationTokenRepository.findByMyUser(myUser);
    }

    @Override
    public void saveToken(MyUser myUser, String token) {
        VerificationToken verificationToken = new VerificationToken(token, myUser);
        //set expiry date to 24 hours
        verificationToken.setExpiryDate(calculateExpiryDate(24 * 60));

        verificationTokenRepository.save(verificationToken);
    }

                // //  //calculate expiry date// // // 
    private Timestamp calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());

    }
}
