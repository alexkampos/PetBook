/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Alkis
 */
@Entity
@Table(name = "friendships")
@XmlRootElement

public class Friendships implements Serializable {

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

    public Friendships() {
    }

    public Friendships(Date datetimeEstablished, MyUser friendOne, MyUser friendTwo) {
        this.datetimeEstablished = datetimeEstablished;
        this.friendOne = friendOne;
        this.friendTwo = friendTwo;
    }

    public Friendships(Integer friendshipId) {
        this.friendshipId = friendshipId;
    }

    public Friendships(Integer friendshipId, Date datetimeEstablished) {
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
    public int hashCode() {
        int hash = 0;
        hash += (friendshipId != null ? friendshipId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Friendships)) {
            return false;
        }
        Friendships other = (Friendships) object;
        if ((this.friendshipId == null && other.friendshipId != null) || (this.friendshipId != null && !this.friendshipId.equals(other.friendshipId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Project.PetBook.Models.Friendships[ friendshipId=" + friendshipId + " ]";
    }
    
}
