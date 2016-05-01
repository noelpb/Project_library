/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Email;

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
    // @ManyToOne
    // @JoinTable(name = "jnd_user_waitinglist", joinColumns = @JoinColumn(name = "email_fk"), inverseJoinColumns = @JoinColumn(name = "waitinglist_fk"))
    // @Column(unique=true)
    private String mail;
    private String pass;
    private Boolean adminUser;
    @OneToMany(mappedBy = "emailU")
    private List<Orders> orders;

    public Users(String name, String surname, String email, String pass, Boolean adminUser) {
        this.name = name;
        this.surname = surname;
        this.mail = email;
        this.pass = pass;
        this.adminUser = adminUser;
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
