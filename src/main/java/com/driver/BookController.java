package com.driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        book.setId(id++);
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBook(@PathVariable Integer id){
        Book book1 = null;
        for(Book book : bookList){
            if(book.getId() == id){
                book1  = book;
            }
        }
        return new ResponseEntity<>(book1,HttpStatus.FOUND);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id){
        Book book = null;
        for(Book b : bookList){
            if(b.getId() == id){
                book = b;
            }
        }
        bookList.remove(book);
//        Iterator<Book> iterator = bookList.iterator();
//        while(iterator.hasNext()){
//            Book book = iterator.next();
//            if(book.getId() == bookId){
//                iterator.remove();
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public ResponseEntity<?> deleteAllBooks(){
        bookList.clear();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam String authorName){
        List<Book> authorBook = new ArrayList<>();
        for(Book book : bookList){
            if(book.getAuthor().equals(authorName)){
                authorBook.add(book);
            }
        }
        return new ResponseEntity<>(authorBook,HttpStatus.OK);
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> getBookByGenre(@RequestParam String genreName){
        List<Book> genreList = new ArrayList<>();
        for(Book book : bookList){
            if(book.getGenre().equals(genreName)){
                genreList.add(book);
            }
        }
        return new ResponseEntity<>(genreList, HttpStatus.OK);
    }
}
