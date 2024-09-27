package com.caroline.awsweb.controller;

import com.caroline.awsweb.models.Author;
import com.caroline.awsweb.models.Books;
import com.caroline.awsweb.repo.AuthorRepo;
import com.caroline.awsweb.service.AuthorService;
import com.caroline.awsweb.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/author")
@RequiredArgsConstructor //så att autowired books repo fungerar
public class AuthorController {

    private final BookService bookService;

    private final AuthorService authorService;

    @GetMapping("")
    public ResponseEntity<List<Author>> getAllAuthors(){

        List <Author> authors = authorService.getAllAuthors();

        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    public ResponseEntity <Optional<Author>> getOneAuthor(@PathVariable long id){
        Optional<Author> author= authorService.getOneAuthor(id);

        return ResponseEntity.ok(author);
    }

    @PostMapping("")
    public ResponseEntity<Author> createNewAuthor(@RequestBody Author newAuthor){
        Author author = authorService.saveAuthor(newAuthor);

        return ResponseEntity.ok(author);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Author> updateOneAuthor(@PathVariable long id, @RequestBody Author newAuthor){

        Author patchedAuthor = authorService.patchAuthor(newAuthor, id);

        return ResponseEntity.ok(patchedAuthor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <String> deleteOneAuthor(@PathVariable long id){

        authorService.removeAuthor(id);

        return ResponseEntity.ok("Author with id " + id + " has been deleted");
    }





}
