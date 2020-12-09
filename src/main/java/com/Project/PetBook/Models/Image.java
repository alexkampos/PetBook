package com.Project.PetBook.Models;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private long imageId;

    @Column(name = "image_path")
    private String imagePath;

    @Column(name = "image_upload_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date imageUploadDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private MyUser myUser;

    public Image() {
    }

    public Image(String imagePath, Date imageUploadDate, MyUser myUser) {
        this.imagePath = imagePath;
        this.imageUploadDate = imageUploadDate;
        this.myUser = myUser;
    }

    public long getImageId() {
        return imageId;
    }

    public void setImageId(long imageId) {
        this.imageId = imageId;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getImageUploadDate() {
        return imageUploadDate;
    }

    public void setImageUploadDate(Date imageUploadDate) {
        this.imageUploadDate = imageUploadDate;
    }

    public MyUser getMyUser() {
        return myUser;
    }

    public void setMyUser(MyUser myUser) {
        this.myUser = myUser;
    }

}
