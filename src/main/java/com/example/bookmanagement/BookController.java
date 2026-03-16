package com.example.bookmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    /** GET /books - Hiển thị danh sách sách */
    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books/list";
    }

    /** GET /books/{id} - Hiển thị chi tiết sách */
    @GetMapping("/{id}")
    public String getBookById(@PathVariable String id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sách với mã: " + id));
        model.addAttribute("book", book);
        return "books/detail";
    }

    /** GET /books/create - Hiển thị form tạo sách mới */
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/form";
    }

    /** POST /books - Thêm sách mới */
    @PostMapping
    public String createBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        bookService.createBook(book);
        redirectAttributes.addFlashAttribute("message", "Thêm sách thành công!");
        return "redirect:/books";
    }

    /** GET /books/{id}/edit - Hiển thị form chỉnh sửa sách */
    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable String id, Model model) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sách với mã: " + id));
        model.addAttribute("book", book);
        return "books/form";
    }

    /** PUT /books/{id} - Cập nhật thông tin sách */
    @PostMapping("/{id}")
    public String updateBook(@PathVariable String id, @ModelAttribute Book bookDetails, RedirectAttributes redirectAttributes) {
        if (!bookService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sách với mã: " + id);
        }
        bookService.updateBook(id, bookDetails);
        redirectAttributes.addFlashAttribute("message", "Cập nhật sách thành công!");
        return "redirect:/books";
    }

    /** DELETE /books/{id} - Xóa sách */
    @PostMapping("/{id}/delete")
    public String deleteBook(@PathVariable String id, RedirectAttributes redirectAttributes) {
        if (!bookService.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sách với mã: " + id);
        }
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("message", "Xóa sách thành công!");
        return "redirect:/books";
    }

    // ==================== REST API ====================

    @RestController
    @RequestMapping("/api/books")
    public static class BookRestController {
        @Autowired
        private BookService bookService;

        @GetMapping
        public List<Book> getAllBooksAPI() {
            return bookService.getAllBooks();
        }

        @GetMapping("/{id}")
        public Book getBookByIdAPI(@PathVariable String id) {
            return bookService.getBookById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sách với mã: " + id));
        }

        @PostMapping
        @ResponseStatus(HttpStatus.CREATED)
        public Book createBookAPI(@RequestBody Book book) {
            return bookService.createBook(book);
        }

        @PutMapping("/{id}")
        public Book updateBookAPI(@PathVariable String id, @RequestBody Book bookDetails) {
            if (!bookService.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sách với mã: " + id);
            }
            return bookService.updateBook(id, bookDetails);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Map<String, String>> deleteBookAPI(@PathVariable String id) {
            if (!bookService.existsById(id)) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sách với mã: " + id);
            }
            bookService.deleteBook(id);
            return ResponseEntity.ok(Map.of("message", "Xóa sách thành công!"));
        }
    }
}
