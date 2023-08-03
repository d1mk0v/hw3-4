package ru.hogwaarts.school.services.api;

import ru.hogwaarts.school.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student addStudent(Student student);

    Optional<Student> findStudent(long id);

    Student editStudent(Student student);

    void deleteStudent(long id);

    List<Student> ageFilter(int age);
}
