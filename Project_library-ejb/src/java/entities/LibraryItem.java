/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Noel
 */
@Entity
public class LibraryItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BookItem bookId;
    private int count;
    private Boolean availability;
    private WaitingListItem waitingList;
    private Users currentUser;

    public LibraryItem() {
    }

      public LibraryItem(Long id, int count, Boolean availability, WaitingListItem waitingList, Users currentUser) {
        this.id = id;
        this.count = count;
        this.availability = availability;
        this.waitingList = waitingList;
        this.currentUser = currentUser;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public WaitingListItem getWaitingList() {
        return waitingList;
    }

    public void setWaitingList(WaitingListItem waitingList) {
        this.waitingList = waitingList;
    }

    public Users getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Users currentUser) {
        this.currentUser = currentUser;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LibraryItem)) {
            return false;
        }
        LibraryItem other = (LibraryItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.LibraryItem[ id=" + id + " ]";
    }
    
}
