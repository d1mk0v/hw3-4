package ru.hogwaarts.school.services;

import ru.hogwaarts.school.models.Faculty;

import java.util.List;
import java.util.Optional;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Optional<Faculty> findFaculty(long id);

    Faculty editFaculty(Faculty faculty);

    void deleteFaculty(long id);

    List<Faculty> findByColor (String color);

}
