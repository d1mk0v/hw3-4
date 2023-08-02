package ru.hogwaarts.school.services;

import ru.hogwaarts.school.models.Student;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    Student findStudent(long id);

    Student editStudent(long id, Student student);

    void deleteStudent(long id);

    List<Student> ageFilter(int age);
}
