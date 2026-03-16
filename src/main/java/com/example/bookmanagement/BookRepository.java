package com.example.bookmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, String> {

    List<Book> findAllByDeletedFalse();

    Optional<Book> findByMaSachAndDeletedFalse(String maSach);

    boolean existsByMaSachAndDeletedFalse(String maSach);
}
