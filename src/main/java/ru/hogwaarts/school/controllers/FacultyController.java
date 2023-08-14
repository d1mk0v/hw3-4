package ru.hogwaarts.school.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.services.api.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Faculty>> findFaculty(@PathVariable long id) {

        Optional<Faculty> faculty = facultyService.findFaculty(id);

        if (faculty.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public Faculty addFaculty(@RequestBody Faculty faculty) {
        return facultyService.addFaculty(faculty);
    }

    @PutMapping("{id}")
    public ResponseEntity<Faculty> editFaculty(@RequestBody Faculty faculty, @PathVariable Long id) {

        Faculty foundFaculty = facultyService.editFaculty(faculty);

        if (foundFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {

        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter/{color}")
    public List<Faculty> findByColor(@PathVariable String color) {
        return facultyService.findByColor(color);
    }

    @GetMapping("/filter")
    public Collection<Faculty> getFacultiesByNameOrColorIgnoreCase(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color) {
        return facultyService.getFacultyByNameOrColorIgnoreCase(name, color);
    }

    @GetMapping("/students-by-id/{id}")
    public Collection<Student> getFacultyStudents(@PathVariable Long id) {
        return facultyService.getFacultyStudents(id);
    }

    @GetMapping("/longest-faculty-name")
    public String longestFacultyName() {
        return facultyService.getLongestFacultyName();
    }
}
