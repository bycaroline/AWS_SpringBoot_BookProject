package com.caroline.awsweb.service;

import com.caroline.awsweb.models.Author;
import com.caroline.awsweb.models.Books;
import com.caroline.awsweb.repo.AuthorRepo;
import com.caroline.awsweb.repo.BooksRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepo authorRepo;

    public List<Author> getAllAuthors(){
        return authorRepo.findAll();
    }

    public Optional<Author> getOneAuthor(long id){
        return Optional.of(authorRepo.findById(id).orElse(new Author()));
        //Optional är med för att hantera null
        //skapar tom bok
    }

    //create and update
    public Author saveAuthor(Author author){
        return authorRepo.save(author);
    }

    public Author patchAuthor(Author author, long id){
        Optional<Author> currentAuthor = authorRepo.findById(id);

        if (!author.getName().equals(currentAuthor.get().getName())) currentAuthor.get().setName(author.getName());

        return authorRepo.save(currentAuthor.get());
    }

    public void removeAuthor(long id){
        authorRepo.deleteById(id);
    }

    public void removeAuthor(Author author){
        authorRepo.deleteById(author.getId());
    }

}
