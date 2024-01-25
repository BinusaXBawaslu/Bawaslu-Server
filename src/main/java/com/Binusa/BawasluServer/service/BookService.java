package com.Binusa.BawasluServer.service;

import com.Binusa.BawasluServer.model.Book;
import com.Binusa.BawasluServer.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }
    public Book updateBook(Long id, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(id);

        if (existingBook.isPresent()) {
            Book bookToUpdate = existingBook.get();
            if (updatedBook.getName() != null) {
                bookToUpdate.setName(updatedBook.getName());
            }
            if (updatedBook.getPhotoUrl() != null) {
                bookToUpdate.setPhotoUrl(updatedBook.getPhotoUrl());
            }

            return bookRepository.save(bookToUpdate);
        } else {
            throw new NoSuchElementException("Buku tidak ditemukan dengan id: " + id);
        }
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
