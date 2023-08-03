package ru.hogwaarts.school.services.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.repositories.FacultyRepository;
import ru.hogwaarts.school.services.api.FacultyService;
import ru.hogwaarts.school.services.impl.FacultyServiceImpl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FacultyServiceImplTest {

    @Test
    public void testAddFaculty() {
        FacultyRepository facultyRepository = Mockito.mock(FacultyRepository.class);
        Faculty faculty = new Faculty();

        when(facultyRepository.save(faculty)).thenReturn(faculty);

        FacultyService facultyService = new FacultyServiceImpl(facultyRepository);
        Faculty addedFaculty = facultyService.addFaculty(faculty);

        assertEquals(faculty, addedFaculty);
        verify(facultyRepository, Mockito.times(1)).save(faculty);
    }

    @Test
    public void testFindFaculty() {
        FacultyRepository facultyRepository = Mockito.mock(FacultyRepository.class);
        Faculty faculty = new Faculty();
        long facultyId = 1L;

        when(facultyRepository.findById(facultyId)).thenReturn(Optional.of(faculty));

        FacultyService facultyService = new FacultyServiceImpl(facultyRepository);
        Optional<Faculty> foundFaculty = facultyService.findFaculty(facultyId);

        assertEquals(Optional.of(faculty), foundFaculty);
        verify(facultyRepository, Mockito.times(1)).findById(facultyId);
    }

    @Test
    public void testEditFaculty() {
        FacultyRepository facultyRepository = Mockito.mock(FacultyRepository.class);
        Faculty faculty = new Faculty();

        when(facultyRepository.save(faculty)).thenReturn(faculty);

        FacultyService facultyService = new FacultyServiceImpl(facultyRepository);
        Faculty editedFaculty = facultyService.editFaculty(faculty);

        assertEquals(faculty, editedFaculty);
        verify(facultyRepository, Mockito.times(1)).save(faculty);
    }

    @Test
    public void testDeleteFaculty() {
        FacultyRepository facultyRepository = Mockito.mock(FacultyRepository.class);
        long facultyId = 1L;

        FacultyService facultyService = new FacultyServiceImpl(facultyRepository);
        facultyService.deleteFaculty(facultyId);

        verify(facultyRepository, Mockito.times(1)).deleteById(facultyId);
    }

    @Test
    public void testFindByColor() {
        FacultyRepository facultyRepository = Mockito.mock(FacultyRepository.class);
        String color = "Blue";
        List<Faculty> faculties = Collections.singletonList(new Faculty());

        when(facultyRepository.findByColor(color)).thenReturn(faculties);

        FacultyService facultyService = new FacultyServiceImpl(facultyRepository);
        List<Faculty> foundFaculties = facultyService.findByColor(color);

        assertEquals(faculties, foundFaculties);
        verify(facultyRepository, Mockito.times(1)).findByColor(color);
    }
}