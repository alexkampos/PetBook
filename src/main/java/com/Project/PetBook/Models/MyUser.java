package com.Project.PetBook.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "users")
@XmlRootElement

public class MyUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;


    @Basic(optional = false)
    @Column(name = "user_name")
    private String userName;

    @Basic(optional = false)
    @Column(name = "user_password")
    private String userPassword;

    @Basic(optional = false)
    @Column(name = "email")
    private String email;

    private boolean enabled;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "userId")
    private ContactInfo contactInfo;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> Roles;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "senderId", fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<FriendRequest> friendSendedRequestsCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "receiverId", fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<FriendRequest> friendRequestCollection1;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friendOne", fetch = FetchType.LAZY)
    private Collection<Friendships> friendshipsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "friendTwo", fetch = FetchType.LAZY)
    private Collection<Friendships> friendshipsCollection1;

    public MyUser() {
    }

    public MyUser(Integer userId) {
        this.userId = userId;
    }

    public MyUser(Integer userId, String userName, String userPassword) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<Role> getRoles() {
        return Roles;
    }

    public void setRoles(Collection<Role> Roles) {
        this.Roles = Roles;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    @XmlTransient
    public Collection<FriendRequest> getFriendSendedRequestsCollection() {
        return friendSendedRequestsCollection;
    }

    public void setFriendSendedRequestsCollection(Collection<FriendRequest> friendSendedRequestsCollection) {
        this.friendSendedRequestsCollection = friendSendedRequestsCollection;
    }

    @XmlTransient
    public Collection<FriendRequest> getFriendRequestCollection1() {
        return friendRequestCollection1;
    }

    public void setFriendRequestCollection1(Collection<FriendRequest> friendRequestCollection1) {
        this.friendRequestCollection1 = friendRequestCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MyUser)) {
            return false;
        }
        MyUser other = (MyUser) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Project.PetBook.Models.Users[ userId=" + userId + " ]";
    }

    @XmlTransient
    public Collection<Friendships> getFriendshipsCollection() {
        return friendshipsCollection;
    }

    public void setFriendshipsCollection(Collection<Friendships> friendshipsCollection) {
        this.friendshipsCollection = friendshipsCollection;
    }

    @XmlTransient
    public Collection<Friendships> getFriendshipsCollection1() {
        return friendshipsCollection1;
    }

    public void setFriendshipsCollection1(Collection<Friendships> friendshipsCollection1) {
        this.friendshipsCollection1 = friendshipsCollection1;
    }

}
