package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.model.User;
import com.codegym.service.CustomerService;
import com.codegym.service.impl.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@SessionAttributes("user")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/list")
    public String index(@ModelAttribute("user") User user,Model model) {
        Iterable<Customer> customerList = customerService.findAll();

        model.addAttribute("customers", customerList);
        model.addAttribute("user",user);
        return "list";
    }
    @GetMapping("/customer/create")
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "create";
    }
    @PostMapping("/customer/save")
    public ModelAndView save(@Validated @ModelAttribute("customer")Customer customer, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("create");

            modelAndView.addObject("error","Added error!");
            return modelAndView;
        }
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("success","Added successfully!");

        return modelAndView;


    }
    @GetMapping("/customer/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        Customer customer = customerService.findById(id);
        model.addAttribute("customer", customerService.findById(id));
        return "/edit";
    }
    @PostMapping("/customer/edit")
    public ModelAndView update(@Validated @ModelAttribute("customer")Customer customer, BindingResult bindingResult) {
        if(bindingResult.hasFieldErrors()){
            ModelAndView modelAndView = new ModelAndView("edit");

            modelAndView.addObject("error","Added error!");
            return modelAndView;
        }
        customerService.save(customer);
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("success","Added successfully!");

        return modelAndView;


    }
    @GetMapping("/customer/{id}/delete")
    public String delete(@PathVariable Long id, Model model,RedirectAttributes redirect) {
        customerService.remove(id);
        redirect.addFlashAttribute("success", "Removed customer successfully!");
        return "redirect:/list";
    }
    @PostMapping("customer/search")
    public String search(@RequestParam String search,Model model){
        model.addAttribute("customers", customerService.searchByName(search));
        return "list";
    }
}
