package ru.hogwaarts.school.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.repositories.StudentRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentServiceImplTest {

    @Test
    public void testAddStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Student student = new Student();

        when(studentRepository.save(student)).thenReturn(student);

        StudentService studentService = new StudentServiceImpl(studentRepository);
        Student addedStudent = studentService.addStudent(student);

        assertEquals(student, addedStudent);
        verify(studentRepository, Mockito.times(1)).save(student);
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
        verify(studentRepository, Mockito.times(1)).findById(studentId);
    }

    @Test
    public void testEditStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        Student student = new Student();

        when(studentRepository.save(student)).thenReturn(student);

        StudentService studentService = new StudentServiceImpl(studentRepository);
        Student editedStudent = studentService.editStudent(student);

        assertEquals(student, editedStudent);
        verify(studentRepository, Mockito.times(1)).save(student);
    }

    @Test
    public void testDeleteStudent() {
        StudentRepository studentRepository = Mockito.mock(StudentRepository.class);
        long studentId = 1L;

        StudentService studentService = new StudentServiceImpl(studentRepository);
        studentService.deleteStudent(studentId);

        verify(studentRepository, Mockito.times(1)).deleteById(studentId);
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
        verify(studentRepository, Mockito.times(1)).findByAge(age);
    }
}