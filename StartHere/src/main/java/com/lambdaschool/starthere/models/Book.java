package com.lambdaschool.starthere.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "books")
public class Book extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bookid;

    private String booktitle;

    private String isbn;

    private int copyyear;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors;

    public Book()
    {
    }

    public Book(String booktitle, String isbn, int copyyear)
    {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copyyear = copyyear;
    }

    public Book(String booktitle, String isbn, int copyyear, List<Author> authors)
    {
        this.booktitle = booktitle;
        this.isbn = isbn;
        this.copyyear = copyyear;
        this.authors = authors;
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

    public List<Author> getAuthors()
    {
        return authors;
    }

    public void setAuthors(List<Author> authors)
    {
        this.authors = authors;
    }
}
