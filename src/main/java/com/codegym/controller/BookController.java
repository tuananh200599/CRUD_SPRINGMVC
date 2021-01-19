package com.codegym.controller;

import com.codegym.model.Author;
import com.codegym.model.Book;
import com.codegym.repository.AuthorRepository;
import com.codegym.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book/")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("create")
    public String getViewCreate(Model model){
        model.addAttribute("Book" ,new Book());
        Iterable<Book> books = bookRepository.findAll();
        model.addAttribute("listAuthor",bookRepository.findAll());
        return "book/create";
    }
    @PostMapping("create")
    public String Create(Book Book){
        Author author = authorRepository.findById(Book.getAuthorId()).orElse(null);
        Book.setAuthor(author);
        bookRepository.save(Book);
        return "book/create";
    }

    @GetMapping("list")
    public String getViewList(Model model){
        model.addAttribute("listBook",bookRepository.findAll());
        return "book/list";
    }
    @GetMapping("edit/{id}")
    public String Getviewedit(Model model, @PathVariable("id")Long id){

        model.addAttribute("book" , bookRepository.findById(id).orElse(null));
        model.addAttribute("listAuthor",authorRepository.findAll());
        return "book/edit";
    }
    @PostMapping("edit")
    public String edit(Model model,Book Book){
        Author author = authorRepository.findById(Book.getAuthorId()).orElse(null);
        Book.setAuthor(author);
        bookRepository.save(Book);
        return "redirect:/book/list";
    }
    @GetMapping("delete/{id}")
    public String delte(Model model, @PathVariable("id")Long id){
        bookRepository.deleteById(id);
        return "redirect:/book/list";
    }
}
