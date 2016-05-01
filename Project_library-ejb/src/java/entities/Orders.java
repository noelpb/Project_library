/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Nudista
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "countOrders", query = "SELECT COUNT(b) FROM Orders b")})
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinTable(name = "jnd_oder_user", joinColumns = @JoinColumn(name = "order_fk"), inverseJoinColumns = @JoinColumn(name = "user_fk"))
    private Users emailU;
    @ManyToMany(mappedBy = "orders")
    private List<LibraryItem> libItem;
    private Date startDate;
    private Date endDate;
    private boolean openOrder;

    public Orders() {
    }

    public Users getEmailU() {
        return emailU;
    }

    public void setEmailU(Users emailU) {
        this.emailU = emailU;
    }

    public List<LibraryItem> getLibItem() {
        return libItem;
    }

    public void setLibItem(List<LibraryItem> libItem) {
        this.libItem = libItem;
    }

    public boolean isOpenOrder() {
        return openOrder;
    }

    public void setOpenOrder(boolean openOrder) {
        this.openOrder = openOrder;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        if (!(object instanceof Orders)) {
            return false;
        }
        Orders other = (Orders) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Orders[ id=" + id + " ]";
    }

}
