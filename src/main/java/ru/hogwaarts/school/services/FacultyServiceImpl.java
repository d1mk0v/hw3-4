package ru.hogwaarts.school.services;

import org.springframework.stereotype.Service;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.repositories.FacultyRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public Optional<Faculty> findFaculty(long id) {
        return facultyRepository.findBy(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(long id) {
        facultyRepository.deleteById(id);
    }
    @Override
    public List<Faculty> colorFilter(String color){
        return facultyRepository.findBy(color);
    }
}
