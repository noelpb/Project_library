/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Noel
 */
@Entity
class WaitingListItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    //@OneToMany(mappedBy = "email")
  //  private List<Users> user;
    @OneToOne(mappedBy = "waitingList")
    private LibraryItem libId;

  /*  public WaitingListItem(List<Users> user) {
        this.user = user;
    }

    public List<Users> getUser() {
        return user;
    }

    public void setUser(List<Users> user) {
        this.user = user;
    }*/

    public LibraryItem getLibId() {
        return libId;
    }

    public void setLibId(LibraryItem libId) {
        this.libId = libId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
