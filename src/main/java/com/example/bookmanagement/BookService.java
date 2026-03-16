package com.example.bookmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAllByDeletedFalse();
    }

    public Optional<Book> getBookById(String id) {
        return bookRepository.findByMaSachAndDeletedFalse(id);
    }

    public boolean existsById(String id) {
        return bookRepository.existsByMaSachAndDeletedFalse(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(String id, Book bookDetails) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setTenSach(bookDetails.getTenSach());
        book.setTacGia(bookDetails.getTacGia());
        book.setDonGia(bookDetails.getDonGia());
        return bookRepository.save(book);
    }

    /** Xóa mềm: đánh dấu deleted = true, không xóa bản ghi khỏi DB */
    public void deleteBook(String id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        book.setDeleted(true);
        bookRepository.save(book);
    }
}
