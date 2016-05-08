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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Noel
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "countLibItems", query = "SELECT COUNT(b) FROM LibraryItem b"),
    @NamedQuery(name = "selectAll", query = "SELECT l FROM LibraryItem AS l")
})
public class LibraryItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    @JoinColumn(name = "isbn_fk")
    private BookItem bookISBN;
    private int count;
    private Boolean availability;
    @OneToOne
    @JoinColumn(name = "id_fk")
    private WaitingListItem waitingList;
    @ManyToMany
    @JoinTable(name = "jnd_lib_order", joinColumns = @JoinColumn(name = "lib_fk"), inverseJoinColumns = @JoinColumn(name = "oder_fk"))
    private List<Orders> orders;

    public LibraryItem() {
    }

    public LibraryItem(BookItem ISBN, int count) {
        this.bookISBN = ISBN;
        this.count = count;
        this.availability = true;
        this.waitingList = null;
    }

    public LibraryItem(BookItem ISBN, int count, Boolean availability, WaitingListItem waitingList) {
        this.bookISBN = ISBN;
        this.count = count;
        this.availability = availability;
        this.waitingList = waitingList;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookItem getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(BookItem bookISBN) {
        this.bookISBN = bookISBN;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
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
        return id + ";" + count + ";" + availability + ";" + bookISBN.getName() + ";" + bookISBN.getName() + ";" + bookISBN.getGenre();
    }

}
