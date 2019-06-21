package com.lambdaschool.starthere.controllers;

import com.lambdaschool.starthere.models.Book;
import com.lambdaschool.starthere.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController
{
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/books", produces = {"application/json"})
    public ResponseEntity<?> listAllBooks()
    {
        List<Book> books = bookService.findAll();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping(value = "/book/{id}", produces = {"application/json"})
    public ResponseEntity<?> getBookById(@PathVariable long id)
    {
        Book b = bookService.findBookById(id);
        return new ResponseEntity<>(b, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_DATA')")
    @PutMapping(value = "/{id}", consumes = {"application/json"})
    public ResponseEntity<?> updateBook(@RequestBody Book updateBook, @PathVariable long id)
    {
        bookService.update(updateBook, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable long id)
    {
        bookService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
