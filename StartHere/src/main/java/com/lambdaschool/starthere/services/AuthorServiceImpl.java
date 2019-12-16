package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "authorService")
public class AuthorServiceImpl implements AuthorService
{
    @Autowired
    private AuthorRepository arepos;

    @Override
    public List<Author> findAll()
    {
        List<Author> list = new ArrayList<>();
        arepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Author findAuthorById(long id)
    {
        return arepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (arepos.findById(id).isPresent())
        {
            arepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Author save(Author author)
    {
        Author newAuthor = new Author();

        newAuthor.setFirstname(author.getFirstname());
        newAuthor.setLastname(author.getLastname());

        ArrayList<Book> newBooks = new ArrayList<>();
        for (Book b : author.getBooks())
        {
            newBooks.add(new Book(b.getBooktitle(), b.getIsbn(), b.getCopyyear()));
        }

        newAuthor.setBooks(newBooks);


        return arepos.save(newAuthor);
    }

    @Override
    public Author update(Author author, long id)
    {
        Author currenA = arepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (currenA != null && id == currenA.getAuthorid())
        {
            if (author.getFirstname() != null)
            {
                currenA.setFirstname(author.getFirstname());
            }

            if (author.getLastname() != null)
            {
                currenA.setLastname(author.getLastname());
            }

            if (author.getBooks().size() > 0)
            {
                for (Book b : author.getBooks())
                {
                    currenA.getBooks().add(new Book(b.getBooktitle(), b.getIsbn(), b.getCopyyear()));
                }
            }

            return arepos.save(currenA);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }
}


