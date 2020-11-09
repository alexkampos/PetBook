
package com.Project.PetBook.Models;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "contact_info")
@XmlRootElement

public class ContactInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ci_id")
    private Integer ciId;
    
    @Basic(optional = false)
    @Column(name = "mobile_phone")
    private String mobilePhone;
    
    @Column(name = "phone")
    private String phone;
 
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    @OneToOne(optional = false)
    private MyUser userId;

    public ContactInfo() {
    }

    public ContactInfo(Integer ciId) {
        this.ciId = ciId;
    }

    public ContactInfo(Integer ciId, String mobilePhone) {
        this.ciId = ciId;
        this.mobilePhone = mobilePhone;
    
    }

    public Integer getCiId() {
        return ciId;
    }

    public void setCiId(Integer ciId) {
        this.ciId = ciId;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MyUser getUserId() {
        return userId;
    }

    public void setUserId(MyUser userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ciId != null ? ciId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactInfo)) {
            return false;
        }
        ContactInfo other = (ContactInfo) object;
        if ((this.ciId == null && other.ciId != null) || (this.ciId != null && !this.ciId.equals(other.ciId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Project.PetBook.Models.ContactInfo[ ciId=" + ciId + " ]";
    }
    
}
