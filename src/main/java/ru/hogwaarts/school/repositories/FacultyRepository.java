package ru.hogwaarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;

import java.util.List;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    List<Faculty> findByColor (String color);
}
