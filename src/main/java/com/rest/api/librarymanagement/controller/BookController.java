package com.rest.api.librarymanagement.controller;

import com.rest.api.librarymanagement.model.Book;
import com.rest.api.librarymanagement.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    //    Request: GET /ping
//    Response: "Up"

//    @GetMapping("/ping")
    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public String ping(){
        return "Up -books";
    }


    //    Request: GET /books
//    Response: Book[]

//    @GetMapping("/books")
    @RequestMapping(value = "/books", method = RequestMethod.GET)
    public List<Book> getAllBooks(){
        return bookRepository.findAll();
    }

    //    Request: GET /book/{id}
//    Response: Book

//    @GetMapping("/book/{id}")
    @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
    public Book getBookById(@PathVariable long id){
        return bookRepository.findById(id);
    }

    //    Request: POST /book Book
    //    Response: Book

//    @PostMapping("/book")
    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public Book saveBook(@RequestBody Book book){
        return bookRepository.save(book);
    }

    //    Request: DELETE /book/{id}
//    Response: void

//    @DeleteMapping("/book/{id}")
    @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
    public void deleteBookById(@PathVariable long id){
        bookRepository.deleteById(id);
    }

    //    Request: PUT /book
    //    Response: Book

//    @PutMapping("/book")
    @RequestMapping(value = "/book", method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }

    /*
    @PutMapping("/book/{id}")
    public Book updateBookById(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }
  */

//    @PutMapping("/book/{id}")
    @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
    Book updateBookById(@RequestBody Book newBook, @PathVariable Long id) {

        return bookRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setRating(newBook.getRating());
            book.setIsbn(newBook.getIsbn());
            return bookRepository.save(book);
        }).orElseGet(() -> {
            newBook.setId(id);
            return bookRepository.save(newBook);
        });
    }
}
