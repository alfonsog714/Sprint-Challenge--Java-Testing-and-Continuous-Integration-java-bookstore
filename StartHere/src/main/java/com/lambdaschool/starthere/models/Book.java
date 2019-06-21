package com.lambdaschool.starthere.models;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    private String booktitle;

    private String isbn;

    private int copyyear;

    public Book()
    {
    }

    public Book(String booktitle, String isbn, int copyyear)
    {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copyyear = copyyear;
    }

    public long getBookid()
    {
        return bookid;
    }

    public void setBookid(long bookid)
    {
        this.bookid = bookid;
    }

    public String getBooktitle()
    {
        return booktitle;
    }

    public void setBooktitle(String booktitle)
    {
        this.booktitle = booktitle;
    }

    public String getIsbn()
    {
        return isbn;
    }

    public void setIsbn(String isbn)
    {
        this.isbn = isbn;
    }

    public int getCopyyear()
    {
        return copyyear;
    }

    public void setCopyyear(int copyyear)
    {
        this.copyyear = copyyear;
    }
}
