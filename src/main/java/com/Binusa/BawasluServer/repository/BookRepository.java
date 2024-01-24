package com.Binusa.BawasluServer.repository;

import com.Binusa.BawasluServer.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
