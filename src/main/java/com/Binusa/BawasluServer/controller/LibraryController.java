package com.Binusa.BawasluServer.controller;

import com.Binusa.BawasluServer.DTO.BookDto;
import com.Binusa.BawasluServer.model.Book;
import com.Binusa.BawasluServer.response.CustomResponse;
import com.Binusa.BawasluServer.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/bawaslu/api/library")
@CrossOrigin(origins = "*")
public class LibraryController {

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ResponseEntity<CustomResponse<List<Book>>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();

        CustomResponse<List<Book>> response = new CustomResponse<>();
        response.setStatus("success");
        response.setCode(200);
        response.setData(books);
        response.setMessage("sukses mengambil semua data buku");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/ById/{id}")
    public ResponseEntity<CustomResponse<Book>> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);

        CustomResponse<Book> response = new CustomResponse<>();
        if (book.isPresent()) {
            response.setStatus("success");
            response.setData(book.get());
            response.setCode(200);
            response.setMessage("sukses mengambil buku by id");
        } else {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Book not found");
        }

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Book>> updateBook(@PathVariable Long id, @RequestBody BookDto updatedBookDto) {
        CustomResponse<Book> response = new CustomResponse<>();
        try {
            Book updatedBook = bookService.updateBook(id, convertDtoToEntity(updatedBookDto));
            response.setStatus("success");
            response.setCode(200);
            response.setData(updatedBook);
            response.setMessage("Sukses Memperbarui Data Buku id" + id);
        } catch (NoSuchElementException e) {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Error memperbarui buku: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<CustomResponse<Book>> addBook(@RequestBody BookDto bookDto) {
        CustomResponse<Book> response = new CustomResponse<>();
        try {
            Book addedBook = bookService.addBook(convertDtoToEntity(bookDto));
            response.setStatus("success");
            response.setCode(200);
            response.setData(addedBook);
            response.setMessage("sukses menambahkan data buku");
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Error adding book: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deleteBookById(@PathVariable Long id) {
        CustomResponse<String> response = new CustomResponse<>();
        try {
            bookService.deleteBookById(id);
            response.setStatus("success");
            response.setCode(200);
            response.setMessage("Book deleted successfully");
        } catch (EmptyResultDataAccessException e) {
            response.setStatus("error");
            response.setCode(404);
            response.setMessage("Book not found");
        } catch (Exception e) {
            response.setStatus("error");
            response.setCode(400);
            response.setMessage("Error deleting book: " + e.getMessage());
        }

        return ResponseEntity.ok(response);
    }

    // Metode konversi DTO ke Entity
    private Book convertDtoToEntity(BookDto bookDto) {
        Book book = new Book();
        book.setName(bookDto.getName());
        book.setPhotoUrl(bookDto.getPhotoUrl());
        return book;
    }
}
