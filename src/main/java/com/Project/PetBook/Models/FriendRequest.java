package com.Project.PetBook.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "friend_requests")
@XmlRootElement
public class FriendRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_request_id")
    private Integer friendRequestId;

    @Basic(optional = false)
    @Column(name = "datetime_sent")
    @Temporal(TemporalType.TIMESTAMP)
    private Date datetimeSent;

    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private FriendRequestStatus statusId;

    @JoinColumn(name = "sender_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private MyUser senderId;

    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    @JsonManagedReference
    private MyUser receiverId;

    public FriendRequest() {
    }

    public FriendRequest(Date datetimeSent, FriendRequestStatus statusId, MyUser senderId, MyUser receiverId) {
        this.datetimeSent = datetimeSent;
        this.statusId = statusId;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    public FriendRequest(Integer friendRequestId) {
        this.friendRequestId = friendRequestId;
    }

    public FriendRequest(Integer friendRequestId, Date datetimeSent) {
        this.friendRequestId = friendRequestId;
        this.datetimeSent = datetimeSent;
    }

    public Integer getFriendRequestId() {
        return friendRequestId;
    }

    public void setFriendRequestId(Integer friendRequestId) {
        this.friendRequestId = friendRequestId;
    }

    public Date getDatetimeSent() {
        return datetimeSent;
    }

    public void setDatetimeSent(Date datetimeSent) {
        this.datetimeSent = datetimeSent;
    }

    public FriendRequestStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(FriendRequestStatus statusId) {
        this.statusId = statusId;
    }

    public MyUser getSenderId() {
        return senderId;
    }

    public void setSenderId(MyUser senderId) {
        this.senderId = senderId;
    }

    public MyUser getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(MyUser receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public String toString() {
        return "com.Project.PetBook.Models.FriendRequest[ friendRequestId=" + friendRequestId + " ]";
    }

}
