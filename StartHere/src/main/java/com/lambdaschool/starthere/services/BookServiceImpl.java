package com.lambdaschool.starthere.services;

import com.lambdaschool.starthere.models.Author;
import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service(value = "bookService")
public class BookServiceImpl implements BookService
{
    @Autowired
    private BookRepository bookrepos;

    @Override
    public List<Book> findAll()
    {
        List<Book> list = new ArrayList<>();
        bookrepos.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public Book findBookById(long id) throws EntityNotFoundException
    {
        return bookrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));
    }

    @Override
    public void delete(long id)
    {
        if (bookrepos.findById(id).isPresent())
        {
            bookrepos.deleteById(id);
        } else
        {
            throw new EntityNotFoundException(Long.toString(id));
        }
    }

    @Transactional
    @Override
    public Book save(Book book)
    {
        Book newBook = new Book();
        newBook.setBooktitle(book.getBooktitle());
        newBook.setCopyyear(book.getCopyyear());
        newBook.setIsbn(book.getIsbn());


        ArrayList<Author> newAuthors = new ArrayList<>();
//        for (Author a : book.getAuthors())
//        {
//            newAuthors.add(new Author(a.getLastname(), a.getFirstname(), a.getBooks()));
//        }
//        newBook.setAuthors(newAuthors);


        return bookrepos.save(newBook);
    }

    @Transactional
    @Override
    public Book update(Book book, long id) // probably going to be an issue deleting prior existing authors from the current book, fix later
    {
        Book currentBook = bookrepos.findById(id).orElseThrow(() -> new EntityNotFoundException(Long.toString(id)));

        if (currentBook != null)
        {
            if (id == currentBook.getBookid())
            {
                if (book.getIsbn() != null)
                {
                    currentBook.setIsbn(book.getIsbn());
                }

                if (book.getBooktitle() != null)
                {
                    currentBook.setBooktitle(book.getBooktitle());
                }

                if (book.getCopyyear() != currentBook.getCopyyear())
                {
                    currentBook.setCopyyear(book.getCopyyear());
                }

                if (book.getAuthors().size() > 0)
                {
                    for (Author a : book.getAuthors())
                    {
                        currentBook.getAuthors().add(new Author(a.getLastname(), a.getFirstname(), a.getBooks()));
                    }
                }

                return bookrepos.save(currentBook);
            } else
            {
                throw new EntityNotFoundException(Long.toString(id));
            }
        } else
        {
            throw new EntityNotFoundException(book.getBooktitle() + "does not exist.");
        }
    }
}
