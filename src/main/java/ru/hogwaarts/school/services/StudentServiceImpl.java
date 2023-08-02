package ru.hogwaarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwaarts.school.exceptions.StudentNotFoundException;
import ru.hogwaarts.school.models.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final Map<Long, Student> students = new HashMap<>();
    private long count = 0;

    @Override
    public Student addStudent(Student student) {

        student.setId(count++);
        students.put(student.getId(), student);
        return student;
    }

    @Override
    public Student findStudent(long id) {

        if (!students.containsKey(id)) {
            throw new StudentNotFoundException("Студент не найден!!!");
        }
        return students.get(id);
    }

    @Override
    public Student editStudent(long id, Student student) {

        if (!students.containsKey(id)) {
            throw new StudentNotFoundException("Студент не найден!!!");
        }

        students.put(id, student);
        return student;
    }

    @Override
    public void deleteStudent(long id) {

        if (!students.containsKey(id)) {
            throw new StudentNotFoundException("Студент не найден!!!");
        }
        students.remove(id);
    }

    @Override
    public List<Student> ageFilter(int age) {
        return students.values().stream()
                .filter(e -> e.getAge() == age)
                .collect(Collectors.toList());
    }
}
