package ru.hogwaarts.school.services.api;

import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Optional<Faculty> findFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);

    List<Faculty> findByColor (String color);

    Collection<Faculty> getFacultyByNameOrColorIgnoreCase(String name, String color);

    Collection<Student> getFacultyStudents(Long id);
}
