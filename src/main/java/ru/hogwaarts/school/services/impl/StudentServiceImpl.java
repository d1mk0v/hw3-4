package ru.hogwaarts.school.services.impl;

import org.springframework.stereotype.Service;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.repositories.StudentRepository;
import ru.hogwaarts.school.services.api.StudentService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findStudent(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> ageFilter(int age) {
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getStudentFaculty(Long id) {
        return studentRepository.findById(id).map(Student::getFaculty).orElse(null);
    }
}
