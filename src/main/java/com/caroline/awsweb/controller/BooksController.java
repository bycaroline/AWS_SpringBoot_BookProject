package com.caroline.awsweb.controller;

import com.caroline.awsweb.models.Books;
import com.caroline.awsweb.repo.AuthorRepo;
import com.caroline.awsweb.repo.BooksRepo;
import com.caroline.awsweb.service.BookService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor //så att autowired books repo fungerar
public class BooksController {

    //@Autowired
    private final BookService bookService;

    private final AuthorRepo authorRepo;

    //get all
    @GetMapping("")
    public ResponseEntity<List<Books>>  getAllBooks(){
        List <Books> books = bookService.getAllBooks();

        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Books>> getOneBook(@PathVariable long id){
        Optional<Books> book = bookService.getOneBook(id);

        return ResponseEntity.ok(book);
    }

    @PostMapping("")
    public ResponseEntity<Books> createNewBook(@RequestBody Books newBook){
        Books book = bookService.saveBook(newBook);

        return ResponseEntity.ok(book);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Books> updateOneBook(@PathVariable long id, @RequestBody Books newBook){

        Books patchedBook = bookService.patchBook(newBook, id);

        return ResponseEntity.ok(patchedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteOneBook(@PathVariable long id){

        bookService.removeBook(id);

        return ResponseEntity.ok("Book with id " + id + " has been deleted");
    }


}
