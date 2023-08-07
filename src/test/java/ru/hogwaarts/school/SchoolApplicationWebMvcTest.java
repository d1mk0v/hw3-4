package ru.hogwaarts.school;

import net.minidev.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.hogwaarts.school.controllers.AvatarController;
import ru.hogwaarts.school.controllers.FacultyController;
import ru.hogwaarts.school.controllers.StudentController;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.repositories.AvatarRepository;
import ru.hogwaarts.school.repositories.FacultyRepository;
import ru.hogwaarts.school.repositories.StudentRepository;
import ru.hogwaarts.school.services.impl.AvatarServiceImpl;
import ru.hogwaarts.school.services.impl.FacultyServiceImpl;
import ru.hogwaarts.school.services.impl.StudentServiceImpl;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
class SchoolApplicationWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @SpyBean
    private StudentServiceImpl studentService;

    @SpyBean
    private FacultyServiceImpl facultyService;

    @SpyBean
    private AvatarServiceImpl avatarService;

    @InjectMocks
    private StudentController studentController;

    @InjectMocks
    private FacultyController facultyController;

    @InjectMocks
    private AvatarController avatarController;

    private Student student;

    private Faculty faculty;

    private final JSONObject studentObject = new JSONObject();

    public final JSONObject facultyObject = new JSONObject();

    @BeforeEach
    void init() throws Exception {

        long id = 1L;
        String name = "name";
        int age = 20;

        String facultyName = "facultyName";
        String color = "color";


        student = new Student(id, name, age);

        studentObject.put("id", id);
        studentObject.put("name", name);
        studentObject.put("age", age);

        faculty = new Faculty(1L, facultyName, color);

        facultyObject.put("id", id);
        facultyObject.put("facultyName", facultyName);
        facultyObject.put("color", color);

        student.setFaculty(faculty);
    }

    @Test
    void testFindStudent() throws Exception {

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    public void testAddStudent() throws Exception {

        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/student/")
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()))
                .andExpect(jsonPath("$.age").value(student.getAge()));
    }

    @Test
    public void testEditStudent() throws Exception {

        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));
        when(studentRepository.save(any(Student.class))).thenReturn(student);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/student/" + student.getId())
                        .content(studentObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(student.getName()));
    }

    @Test
    public void testAgeFilter() throws Exception {

        List<Student> list = new ArrayList<>(List.of(
                new Student(1L, "1", 1),
                new Student(2L, "2", 1),
                new Student(3L, "3", 1)
        ));

        when(studentRepository.findByAge(1)).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + student.getAge())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()));
    }

    @Test
    public void testGetStudentsByAgeBetween() throws Exception {

        List<Student> list = new ArrayList<>(List.of(
                new Student(1L, "1", 1),
                new Student(2L, "2", 1),
                new Student(3L, "3", 1)
        ));

        when(studentRepository.findByAgeBetween(0, 2)).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age/between/0,2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()));
    }

    @Test
    public void testGetStudentFaculty() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/faculty/" + student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()));
    }

    @Test
    public void testFindFaculty() throws Exception {

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/" + faculty.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }

    @Test
    public void testAddFaculty() throws Exception {

        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/faculty/")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()))
                .andExpect(jsonPath("$.color").value(faculty.getColor()));
    }

    @Test
    public void testEditFaculty() throws Exception {

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));
        when(facultyRepository.save(any(Faculty.class))).thenReturn(faculty);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/faculty/")
                        .content(facultyObject.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(faculty.getName()));
    }

    @Test
    public void testFindByColor() throws Exception {

        List<Faculty> list = new ArrayList<>(List.of(
                new Faculty(1L, "1", "red"),
                new Faculty(2L, "2", "red"),
                new Faculty(3L, "3", "red")
        ));

        when(facultyRepository.findByColor("red")).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/red")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()));

    }

    @Test
    public void testGetFacultiesByNameOrColourIgnoreCase() throws Exception {

        List<Faculty> list = new ArrayList<>(List.of(
                new Faculty(1L, "red", "green"),
                new Faculty(2L, "yellow", "red"),
                new Faculty(3L, "3", "red")
        ));

        when(facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(anyString(), anyString())).thenReturn(list);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty/filter" + faculty.getColor())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()));
    }

    @Test
    public void testGetFacultyStudents() throws Exception {

        List<Student> list = new ArrayList<>(List.of(
                new Student(1L, "1", 1),
                new Student(2L, "2", 1),
                new Student(3L, "3", 1)
        ));

        faculty.setStudents(list);

        when(facultyRepository.findById(any(Long.class))).thenReturn(Optional.of(faculty));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/faculty//students-by-id/" + student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(faculty.getStudents().size()));
    }
}