package ru.hogwaarts.school.services.impl;

import org.springframework.stereotype.Service;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.repositories.FacultyRepository;
import ru.hogwaarts.school.services.api.FacultyService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public Optional<Faculty> findFaculty(Long id) {
        return facultyRepository.findById(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }
    @Override
    public List<Faculty> findByColor (String color){
        return facultyRepository.findByColor(color);
    }

    @Override
    public Collection<Faculty> getFacultyByNameOrColorIgnoreCase(String name, String color) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getFacultyStudents(Long id) {
        return facultyRepository.findById(id).map(Faculty::getStudents).orElse(null);
    }
}
