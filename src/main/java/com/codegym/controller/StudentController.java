package com.codegym.controller;

import com.codegym.model.ClassStudent;
import com.codegym.model.Student;
import com.codegym.repository.ClassStudentRepository;
import com.codegym.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student/")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassStudentRepository classStudentRepository;

    @GetMapping("create")
    public String getViewCreate(Model model){
        model.addAttribute("Student" ,new Student());
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("listClass",classStudentRepository.findAll());
        return "student/create";
    }
    @PostMapping("create")
    public String Create(Student Student){
        ClassStudent classStudent = classStudentRepository.findById(Student.getClassStudentId()).orElse(null);
        Student.setClassStudent(classStudent);
        studentRepository.save(Student);
        return "student/create";
    }

    @GetMapping("list")
    public String getViewList(Model model){
        model.addAttribute("listStudent",studentRepository.findAll());
        return "student/list";
    }
    @GetMapping("edit/{id}")
    public String Getviewedit(Model model, @PathVariable("id")Long id){

        model.addAttribute("student" , studentRepository.findById(id).orElse(null));
        model.addAttribute("listClass",classStudentRepository.findAll());
        return "student/edit";
    }
    @PostMapping("edit")
    public String edit(Model model,Student Student){
        ClassStudent classStudent = classStudentRepository.findById(Student.getClassStudentId()).orElse(null);
        Student.setClassStudent(classStudent);
        studentRepository.save(Student);
        return "redirect:/student/list";
    }
    @GetMapping("delete/{id}")
    public String delte(Model model, @PathVariable("id")Long id){
        studentRepository.deleteById(id);
        return "redirect:/student/list";
    }
}
