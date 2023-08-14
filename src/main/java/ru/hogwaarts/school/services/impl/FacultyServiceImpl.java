package ru.hogwaarts.school.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.repositories.FacultyRepository;
import ru.hogwaarts.school.services.api.FacultyService;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    Logger logger = LoggerFactory.getLogger(FacultyServiceImpl.class);

    public FacultyServiceImpl(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public Faculty addFaculty(Faculty faculty) {
        logger.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public Optional<Faculty> findFaculty(Long id) {
        logger.info("Was invoked method for find faculty");
        return facultyRepository.findById(id);
    }

    @Override
    public Faculty editFaculty(Faculty faculty) {
        logger.info("Was invoked method for edit faculty");
        return facultyRepository.save(faculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        logger.info("Was invoked method for delete faculty");
        facultyRepository.deleteById(id);
    }
    @Override
    public List<Faculty> findByColor (String color){
        logger.info("Was invoked method for find faculty by color");
        return facultyRepository.findByColor(color);
    }

    @Override
    public Collection<Faculty> getFacultyByNameOrColorIgnoreCase(String name, String color) {
        logger.info("Was invoked method for get faculty by name or color");
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(name, color);
    }

    @Override
    public Collection<Student> getFacultyStudents(Long id) {
        logger.info("Was invoked method for get faculty students");
        return facultyRepository.findById(id).map(Faculty::getStudents).orElse(null);
    }

    @Override
    public String getLongestFacultyName() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparingInt(String::length))
                .orElse("");
    }
}
