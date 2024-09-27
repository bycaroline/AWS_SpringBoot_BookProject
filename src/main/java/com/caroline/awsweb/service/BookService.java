package com.caroline.awsweb.service;

import com.caroline.awsweb.models.Books;
import com.caroline.awsweb.repo.BooksRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BooksRepo booksRepo;

    public List<Books> getAllBooks(){
        return booksRepo.findAll();
    }

    public Optional<Books> getOneBook(long id){
        return Optional.of(booksRepo.findById(id).orElse(new Books()));
        //Optional är med för att hantera null
        //skapar tom bok
    }

    //create and update
    public Books saveBook(Books book){
        return booksRepo.save(book);
    }

    public Books patchBook(Books book, long id){
        Optional<Books> currentBook = booksRepo.findById(id);

        if (!book.getTitle().equals(currentBook.get().getTitle())) currentBook.get().setTitle(book.getTitle());
        if (!book.getISBN().equals(currentBook.get().getISBN())) currentBook.get().setISBN(book.getISBN());

        return booksRepo.save(currentBook.get());
    }

    public void removeBook(long id){
        booksRepo.deleteById(id);
    }

    public void removeBook(Books book){
        booksRepo.deleteById(book.getId());
    }


}
