package ru.hogwaarts.school;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.hogwaarts.school.controllers.FacultyController;
import ru.hogwaarts.school.controllers.StudentController;
import ru.hogwaarts.school.models.Faculty;
import ru.hogwaarts.school.models.Student;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SchoolApplicationTest {

        @LocalServerPort
        private int port;

        @Autowired
        private StudentController studentController;

        @Autowired
        private FacultyController facultyController;

        @Autowired
        private TestRestTemplate testRestTemplate;

        @Test
        void contextLoads() throws Exception{

            Assertions.assertThat(studentController).isNotNull();
            Assertions.assertThat(facultyController).isNotNull();
        }

        @Test
        void addStudentTest() {

                Student student = new Student(1L, "Harry Potter", 16);

                Assertions
                        .assertThat(this.testRestTemplate.postForObject(
                                "http://localhost:" + port + "/student", student, Student.class))
                        .isNotNull();
        }

        @Test
        void findStudentTest() {

                Assertions
                        .assertThat(this.testRestTemplate.getForObject(
                                "http://localhost:" + port + "/student/?id=1", Student.class))
                        .isNotNull();
        }

        @Test
        void editStudentTest() {

        Student student = new Student(1L, "Harry Potter", 16);

                ResponseEntity<Student> studentEntity = testRestTemplate.exchange(
                        "http://localhost:" + port + "/student",
                        HttpMethod.PUT,
                        new HttpEntity<>(student),
                        Student.class);
        }

        @Test
        void deleteStudentTest() throws Exception {

                ResponseEntity<Void> response = testRestTemplate.exchange("/student/1", HttpMethod.DELETE,
                        null, Void.class, 1L);
                assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        @Test
        void ageFilterTest() {
                Assertions
                        .assertThat(this.testRestTemplate.getForObject(
                                "http://localhost:" + port + "/student-age?age=16", Student.class))
                        .isNotNull();
        }

        @Test
        void getStudentsByAgeBetweenTest() {
                Assertions
                        .assertThat(this.testRestTemplate.getForObject(
                                "http://localhost:" + port + "/student/age/between?min=10&max=30", Collection.class))
                        .isNotNull();
        }

        @Test
        void addFacultyTest() throws Exception {

                Faculty faculty = new Faculty(1L, "name", "color");

                Assertions
                        .assertThat(this.testRestTemplate.postForObject(
                                "http://localhost:" + port + "/faculty", faculty, Faculty.class))
                        .isNotNull();
        }

        @Test
        void findFacultyTest() throws Exception {

                Assertions
                        .assertThat(this.testRestTemplate.getForObject("http://localhost:" + port + "/faculty/?id=1", String.class))
                        .isNotNull();
        }

        @Test
        void editFacultyTest() {

                Faculty faculty = new Faculty(1L, "name", "color");

                ResponseEntity<Student> studentEntity = testRestTemplate.exchange(
                        "http://localhost:" + port + "/faculty",
                        HttpMethod.PUT,
                        new HttpEntity<>(faculty),
                        Student.class);
        }

        @Test
        void getFacultiesByNameOrColorIgnoreCaseTest() throws Exception {

                Assertions
                        .assertThat(this.testRestTemplate.getForObject(
                        "http://localhost:" + port + "/faculty?name=name&color=color", Faculty.class))
                        .isNotNull();
        }

        @Test
        void getFacultyStudentsTest() throws Exception {

                Assertions
                        .assertThat(this.testRestTemplate.getForEntity("http://localhost:" + port + "/students-by-id/1", Faculty.class))
                        .isNotNull();
        }

        @Test
        void getStudentsNameStartingWithATest() {

                Assertions
                        .assertThat(this.testRestTemplate.getForEntity(
                                "http://localhost:" + port + "/student/filter/A", String[].class));
        }
}