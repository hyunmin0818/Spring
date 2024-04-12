package com.codingbox.test.controller;

import com.codingbox.test.repository.Student;
import com.codingbox.test.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/html/students")
public class StudentController {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentController(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @GetMapping("/")
    public String index(){
        return "../static/index";
    }

    @GetMapping
    public String students(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "html/students";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return "html/studentAddForm";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute("student")
                       Student student, RedirectAttributes redirectAttributes) {
        Student savedStudent = studentRepository.save(student);

        redirectAttributes.addAttribute("studentId",savedStudent.getStudentId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/student/students/" + savedStudent.getStudentId();
    }

    @GetMapping("/{studentId}")
    public String student(@PathVariable long studentId  ,Model model) {
        Student student = studentRepository.findById(studentId);
        model.addAttribute("student",student);
        return "html/student";
    }

    @GetMapping("/{studentId}/edit")
    public String editForm(@PathVariable Long studentId, Model model){
        Student student = studentRepository.findById(studentId);
        model.addAttribute("student", student);
        return "html/studentEditForm";
    }

    @PostMapping("/{studentId}/edit")
    public String edit(@PathVariable Long studentId, @ModelAttribute Student student){
        studentRepository.update(studentId, student);
        return "redirect:/student/students/{studentId}";
    }

    @PostConstruct
    public void init() {
        studentRepository.save(new Student(1L, "학생A", 20, 4, "01012345671", "역삼1동"));
        studentRepository.save(new Student(2L, "학생B", 22, 5, "01012345672", "역삼2동"));
        studentRepository.save(new Student(3L,"학생C", 23, 6, "01012345673", "역삼3동"));
    }
}