package ru.hogwaarts.school.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {
        logger.info("Was invoked method for create student");
//        logger.debug("");
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> findStudent(Long id) {
        logger.info("Was invoked method for find student");
        return studentRepository.findById(id);
    }

    @Override
    public Student editStudent(Student student) {
        logger.info("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long id) {
        logger.info("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> ageFilter(int age) {
        logger.info("Was invoked method for filter students by age");
        return studentRepository.findByAge(age);
    }

    @Override
    public Collection<Student> getStudentsByAgeBetween(int min, int max) {
        logger.info("Was invoked method for filter students by age between");
        return studentRepository.findByAgeBetween(min, max);
    }

    @Override
    public Faculty getStudentFaculty(Long id) {
        logger.info("Was invoked method for filter students by age between");
        return studentRepository.findById(id).map(Student::getFaculty).orElse(null);
    }

    @Override
    public Integer getNumberOfAllStudents() {
        return studentRepository.getNumberOfAllStudents();
    }

    @Override
    public Double getAverageAgeOfStudents() {
        return studentRepository.getAverageAgeOfStudents();
    }

    @Override
    public List<Student> getLastFiveStudents() {
        return studentRepository.getLastFiveStudents();
    }
}
