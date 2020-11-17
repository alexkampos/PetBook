
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "friend_request_status")
@XmlRootElement
public class FriendRequestStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "status_id")
    private Integer statusId;
    
    @Basic(optional = false)
    @Column(name = "status_name")
    private String statusName;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "statusId", fetch = FetchType.LAZY)
    @JsonBackReference
    private Collection<FriendRequest> friendRequestCollection;

    public FriendRequestStatus() {
    }

    public FriendRequestStatus(Integer statusId) {
        this.statusId = statusId;
    }

    public FriendRequestStatus(Integer statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @XmlTransient
    public Collection<FriendRequest> getFriendRequestCollection() {
        return friendRequestCollection;
    }

    public void setFriendRequestCollection(Collection<FriendRequest> friendRequestCollection) {
        this.friendRequestCollection = friendRequestCollection;
    }

    @Override
    public String toString() {
        return "com.Project.PetBook.Models.FriendRequestStatus[ statusId=" + statusId + " ]";
    }
    
}
