package ru.hogwaarts.school;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@AutoConfigureWebMvc
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
    void init() throws JSONException {

        long id = 1L;
        String name = "name";
        int age = 20;

        String facultyName = "facultyName";
        String color = "color";


        student = new Student(id, name, age);

        studentObject.put("id", student.getId());
        studentObject.put("name", student.getName());
        studentObject.put("age", student.getAge());

        faculty = new Faculty(1L, facultyName, color);

        facultyObject.put("id", id);
        facultyObject.put("facultyName", facultyName);
        facultyObject.put("color", color);

        student.setFaculty(faculty);
    }

    @Test
    void testFindStudent() throws Exception {

        when(studentRepository.findById(any(long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/" + student.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(student.getId()))
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
                new Student(1L, "name1", 20),
                new Student(2L, "name2", 25),
                new Student(3L, "name3", 20)
        ));

        when(studentRepository.findByAge(20)).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/age/20")
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
                        .get("/student/age/between/?min=0&max=2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(list.size()));
    }

    @Test
    public void testGetStudentFaculty() throws Exception {
        when(studentRepository.findById(any(Long.class))).thenReturn(Optional.of(student));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/students-by-faculty/" + student.getId())
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
                        .put("/faculty/1")
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
                        .get("/faculty/filter/red")
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
                        .get("/faculty/filter?name=red&color=green")
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

    @Test
    void testGetNumberOfAllStudents() throws Exception {
        when(studentRepository.getNumberOfAllStudents()).thenReturn(16);
        String result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/number-of-all-students"))
                .andExpect(status().isOk())
                .andReturn().getResponse()
                .getContentAsString();
        assertEquals("16", result);
    }

    @Test
    void testGetAverageAgeOfStudents() throws Exception {
        when(studentRepository.getAverageAgeOfStudents()).thenReturn(15.5);
        String result = mockMvc.perform(MockMvcRequestBuilders
                .get("/student/average-age")).andReturn().getResponse().getContentAsString();
        assertEquals("15.5", result);
    }

    @Test
    void getLastFiveStudents() throws Exception {
        List<Student> list = new ArrayList<>(List.of(
                new Student(1L, "1", 1),
                new Student(2L, "2", 1),
                new Student(3L, "3", 1),
                new Student(4L, "4", 1),
                new Student(5L, "3", 1)
        ));
        when(studentRepository.getLastFiveStudents()).thenReturn(list);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                        .get("/student/last-five-students")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertEquals(result.getResponse().getContentAsString(), new ObjectMapper().writeValueAsString(list));

    }
}