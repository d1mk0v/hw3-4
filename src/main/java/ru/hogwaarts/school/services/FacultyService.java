package ru.hogwaarts.school.services;

import ru.hogwaarts.school.models.Faculty;

import java.util.List;

public interface FacultyService {

    Faculty addFaculty(Faculty faculty);

    Faculty findFaculty(long id);

    Faculty editFaculty(long id, Faculty faculty);

    void deleteFaculty(long id);

    List<Faculty> colorFilter(String color);

}
