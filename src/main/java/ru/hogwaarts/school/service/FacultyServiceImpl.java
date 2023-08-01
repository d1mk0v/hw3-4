package ru.hogwaarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwaarts.school.exception.FacultyNotFoundException;
import ru.hogwaarts.school.model.Faculty;

import java.util.HashMap;
import java.util.Map;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final Map<Long, Faculty> faculties = new HashMap<>();
    private long count = 0;

    @Override
    public Faculty addFaculty(Faculty faculty) {
        faculty.setId(count++);
        faculties.put(faculty.getId(), faculty);
        return faculty;
    }

    @Override
    public Faculty findFaculty(long id) {

        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException("Факультет не найден!!!");
        }
        return faculties.get(id);
    }

    @Override
    public Faculty editFaculty(long id, Faculty faculty) {

        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException("Факультет не найден!!!");
        }

        faculties.put(id, faculty);
        return faculty;
    }

    @Override
    public void deleteFaculty(long id) {

        if (!faculties.containsKey(id)) {
            throw new FacultyNotFoundException("Факультет не найден!!!");
        }
        faculties.remove(id);
    }
}
