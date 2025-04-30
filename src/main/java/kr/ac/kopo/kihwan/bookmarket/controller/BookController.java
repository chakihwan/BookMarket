package kr.ac.kopo.kihwan.bookmarket.controller;

import kr.ac.kopo.kihwan.bookmarket.domain.Book;
import kr.ac.kopo.kihwan.bookmarket.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String requestBookList(Model model) {
        List<Book> bookList = bookService.getAllBookList();
        model.addAttribute("bookList", bookList);
        return "books";
    }

    @GetMapping("/all")
    public ModelAndView requestAllBookList() {
        ModelAndView modelV = new ModelAndView();
        modelV.setViewName("books");
        List<Book> bookList = bookService.getAllBookList();
        modelV.addObject("bookList", bookList);
        return modelV;
    }

    @GetMapping("/book")
    public String requestBookById(@RequestParam("id") String bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "book";
    }
    @GetMapping("/{category}")
    public String requestBookListByCategory(@PathVariable(
            "category") String category, Model model) {
        List<Book> booksByCategory = bookService.getBookListByCategory(category);
        model.addAttribute("bookList", booksByCategory);
        return "books";
    }

    @GetMapping("/filter/{bookFilter}")
    public String requestBookListByFilter(@MatrixVariable(
            pathVar = "bookFilter")Map<String, List<String>> bookFilter, Model model) {
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);
        return "books";
    }

    @GetMapping("/add")
    public String requestAddBookForm(){
        return "addBook";
    }

    @PostMapping("/add")
    public String requestSubmitNewBook(@ModelAttribute("book") Book book){
        bookService.setNewBook(book);
        return "redirect:/books";
    }

    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("addTitle", "신규 도서 등록");
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.setAllowedFields("bookID", "name", "unitPrice", "author", "publisher", "description", "category", "unitInStock", "releaseDate", "condition");
    }

}
