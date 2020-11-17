
package com.Project.PetBook.Models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "friendships")
@XmlRootElement

public class Friendship implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "friendship_id")
    private Integer friendshipId;
    @Basic(optional = false)
    @Column(name = "datetime_established")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeEstablished;
    @JoinColumn(name = "friend_one", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MyUser friendOne;
    @JoinColumn(name = "friend_two", referencedColumnName = "user_id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MyUser friendTwo;

    public Friendship() {
    }

    public Friendship(Date datetimeEstablished, MyUser friendOne, MyUser friendTwo) {
        this.datetimeEstablished = datetimeEstablished;
        this.friendOne = friendOne;
        this.friendTwo = friendTwo;
    }

    public Friendship(Integer friendshipId) {
        this.friendshipId = friendshipId;
    }

    public Friendship(Integer friendshipId, Date datetimeEstablished) {
        this.friendshipId = friendshipId;
        this.datetimeEstablished = datetimeEstablished;
    }

    public Integer getFriendshipId() {
        return friendshipId;
    }

    public void setFriendshipId(Integer friendshipId) {
        this.friendshipId = friendshipId;
    }

    public Date getDatetimeEstablished() {
        return datetimeEstablished;
    }

    public void setDatetimeEstablished(Date datetimeEstablished) {
        this.datetimeEstablished = datetimeEstablished;
    }

    public MyUser getFriendOne() {
        return friendOne;
    }

    public void setFriendOne(MyUser friendOne) {
        this.friendOne = friendOne;
    }

    public MyUser getFriendTwo() {
        return friendTwo;
    }

    public void setFriendTwo(MyUser friendTwo) {
        this.friendTwo = friendTwo;
    }

    @Override
    public String toString() {
        return "com.Project.PetBook.Models.Friendships[ friendshipId=" + friendshipId + " ]";
    }
    
}
