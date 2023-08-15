package ru.hogwaarts.school.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.services.api.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Student>> findStudent(@PathVariable long id) {

        Optional<Student> student = studentService.findStudent(id);

        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {

        return studentService.addStudent(student);
    }

    @PutMapping("{id}")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable long id) {

        Student foundStudent = studentService.editStudent(student);

        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable long id) {

        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/age/{age}")
    public List<Student> ageFilter(@PathVariable int age) {
        return studentService.ageFilter(age);
    }

    @GetMapping("/age/between")
    public Collection<Student> getStudentsByAgeBetween(@RequestParam int min,
                                                       @RequestParam int max) {
        return studentService.getStudentsByAgeBetween(min, max);
    }

    @GetMapping("/students-by-faculty/{id}")
    public Faculty getStudentFaculty(@PathVariable Long id) {
        return studentService.getStudentFaculty(id);
    }

    @GetMapping("/number-of-all-students")
    public Integer getNumberOfAllStudents() {
        return studentService.getNumberOfAllStudents();
    }

    @GetMapping("/average-age")
    public Double getAverageAgeOfStudents() {
        return studentService.getAverageAgeOfStudents();
    }

    @GetMapping("/last-five-students")
    public List<Student> getLastFiveStudents() {
        return studentService.getLastFiveStudents();
    }

    @GetMapping("/filter/A")
    public List<String> getStudentsNameStartingWithA() {
        return studentService.getStudentsNameStartingWithA();
    }

    @GetMapping("/get-average-age")
    public Double getAverageAgeOfAllStudents() {
        return studentService.getAverageAgeOfAllStudents();
    }

    @GetMapping("/print-student-non-synchronized")
    public void printStudentsNonSynchronized() {
        studentService.printStudentsNonSynchronized();
    }

//    @GetMapping("/print-student-synchronized")
//    public void printStudentsSynchronized() {
//        studentService.printStudentsSynchronized();
//    }
}
