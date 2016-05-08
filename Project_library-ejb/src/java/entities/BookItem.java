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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Nudista
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "countBooks", query = "SELECT COUNT(b) FROM BookItem b")})
public class BookItem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "book")
    private List<AuthorItem> writer;
    private int pages;
    @OneToOne(mappedBy = "bookISBN")
    private LibraryItem libItem;
    @Column(unique = true)
    private String ISBN;
    @Enumerated(EnumType.STRING)
    private Genre genre;

    public BookItem() {
    }

    public BookItem(String name, int pages, String ISBN, Genre genre) {
        this.name = name;
        this.pages = pages;
        this.ISBN = ISBN;
        this.genre = genre;
        this.writer = new ArrayList<>();
    }

    public List<AuthorItem> getWriter() {
        return writer;
    }
    
     
    public void setWriter(List<AuthorItem> writer) {
        this.writer = writer;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String writersToString() {
        String result = "";
        for (AuthorItem authorItem : writer) {
            result = result + ";" + authorItem.getName();
        }
        return result;
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
        if (!(object instanceof BookItem)) {
            return false;
        }
        BookItem other = (BookItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "book.BookItem[ id=" + name + " ]";
    }

}
