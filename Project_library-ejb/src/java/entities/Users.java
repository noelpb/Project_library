/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Noel
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "countUsers", query = "SELECT COUNT(u) FROM Users u")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String surname;
    @Column(unique=true)
    private String mail;
    private String pass;
    private Boolean adminUser;
    @OneToMany(mappedBy = "user")
    private List<Orders> orders;
  private boolean masterAdmin;
     @ManyToOne
    @JoinColumn(name = "waiting_fk")
    private WaitingListItem waitlist;

    public Users(String name, String surname, String mail, String pass) {
        this.name = name;
        this.surname = surname;
        this.mail = mail;
        this.pass = pass;
        this.adminUser = false;
        this.orders = new ArrayList<>();
        this.waitlist=null;
      this.masterAdmin=false;
    }

    public Users(String name, String surname, String mail, String pass, Boolean adminUser) {
        this.surname = surname;
        this.mail = mail;
        this.pass = pass;
        this.adminUser = adminUser;
        this.orders = new ArrayList<>();
        this.name = name;
        this.waitlist=null;
       this.masterAdmin=false;
    }

    public Users(boolean masterAdmin) {
         this.surname = "Director";
        this.mail = "director@gmail.com";
        this.pass = "password";
        this.adminUser = true;
        this.orders = new ArrayList<>();
        this.name = "Master";
        this.waitlist=null;
        this.masterAdmin = true;
    }
    
    

    public WaitingListItem getWaitlist() {
        return waitlist;
    }

    public void setWaitlist(WaitingListItem waitlist) {
        this.waitlist = waitlist;
    }

    
    
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

  

    public Users() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(Boolean adminUser) {
        this.adminUser = adminUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
