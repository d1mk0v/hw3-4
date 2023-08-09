package ru.hogwaarts.school.services.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.repositories.StudentRepository;
import ru.hogwaarts.school.services.api.StudentService;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class StudentServiceImplTest {

    @Test
    public void testAddStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Student student = new Student();

        when(studentRepository.save(student)).thenReturn(student);

        StudentService studentService = new StudentServiceImpl(studentRepository);
        Student addedStudent = studentService.addStudent(student);

        assertEquals(student, addedStudent);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testFindStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Student student = new Student();
        long studentId = 1L;

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        StudentService studentService = new StudentServiceImpl(studentRepository);
        Optional<Student> foundStudent = studentService.findStudent(studentId);

        assertEquals(Optional.of(student), foundStudent);
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    public void testEditStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Student student = new Student();

        when(studentRepository.save(student)).thenReturn(student);

        StudentService studentService = new StudentServiceImpl(studentRepository);
        Student editedStudent = studentService.editStudent(student);

        assertEquals(student, editedStudent);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    public void testDeleteStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        long studentId = 1L;

        StudentService studentService = new StudentServiceImpl(studentRepository);
        studentService.deleteStudent(studentId);

        verify(studentRepository, times(1)).deleteById(studentId);
    }

    @Test
    public void testAgeFilter() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        int age = 18;
        List<Student> students = Collections.singletonList(new Student());

        when(studentRepository.findByAge(age)).thenReturn(students);

        StudentService studentService = new StudentServiceImpl(studentRepository);
        List<Student> filteredStudents = studentService.ageFilter(age);

        assertEquals(students, filteredStudents);
        verify(studentRepository, times(1)).findByAge(age);
    }

    @Test
    void testGetNumberOfAllStudents() {

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        StudentService studentService = new StudentServiceImpl(studentRepository);

        int expectedNumberOfStudents = 10;
        when(studentRepository.getNumberOfAllStudents()).thenReturn(expectedNumberOfStudents);

        int actualNumberOfStudents = studentService.getNumberOfAllStudents();

        verify(studentRepository, times(1)).getNumberOfAllStudents();
        Assertions.assertEquals(expectedNumberOfStudents, actualNumberOfStudents);
    }

    @Test
    void testGetAverageAgeOfStudents() {

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        StudentService studentService = new StudentServiceImpl(studentRepository);

        double expectedAverageAge = 23.5;
        when(studentRepository.getAverageAgeOfStudents()).thenReturn(expectedAverageAge);

        double actualAverageAge = studentService.getAverageAgeOfStudents();

        verify(studentRepository, times(1)).getAverageAgeOfStudents();
        Assertions.assertEquals(expectedAverageAge, actualAverageAge);
    }

    @Test
    void testGetLastFiveStudents() {

        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        StudentService studentService = new StudentServiceImpl(studentRepository);

        List<Student> expectedLastFiveStudents = Arrays.asList(
                new Student(1L, "Name1", 15),
                new Student(2L, "Name2", 16),
                new Student(3L, "Name3", 17),
                new Student(4L, "Name1", 18),
                new Student(5L, "Name1", 19)
        );
        when(studentRepository.getLastFiveStudents()).thenReturn(expectedLastFiveStudents);

        List<Student> actualLastFiveStudents = studentService.getLastFiveStudents();

        verify(studentRepository, times(1)).getLastFiveStudents();
        Assertions.assertEquals(expectedLastFiveStudents, actualLastFiveStudents);
    }
}