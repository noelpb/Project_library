/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package book;

/**
 *
 * @author Nudista
 */
public class Book {
    
private String name;
private String writer;
private int pages;
private String ISBN;
private int number;
private int year;

    public Book(String name, String writer, int pages, String ISBN, int number, int year) {
        this.name = name;
        this.writer = writer;
        this.pages = pages;
        this.ISBN = ISBN;
        this.number = number;
        this.year = year;
    }

    public Book() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return name + ";" + writer + ";" + pages + ";" + ISBN + ";" + number + ";" + year;
    }


    
}
