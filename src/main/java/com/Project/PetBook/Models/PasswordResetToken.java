package com.Project.PetBook.Models;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class PasswordResetToken implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prt_id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = MyUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private MyUser myUser;

    @Column(nullable = false)
    private Date expiryDate;

    public Long getPrtId() {
        return prt_id;
    }

    public void setPrtId(Long prtId) {
        this.prt_id = prtId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public MyUser getMyUser() {
        return myUser;
    }
    
    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }
    
    public Date getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    public void setExpiryDate(int minutes) {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, minutes);
        this.expiryDate = now.getTime();
    }
    
    public boolean isExpired() {
        return new Date().after(this.expiryDate);
    }

}
