package ru.hogwaarts.school;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}

//		3.2. Создание API. Swagger UI, Postman
//
//		> Привет! На связи домашнее задание урока 3.2. Создание API. Swagger UI, Postman.
//
//		Цели сегодняшней домашней работы:
//		1) создать полноценное RESTful-приложение, используя структуры model, service,
//		controller;
//		2) добавить эндпоинты для CRUD-операций над сущностями; добавить swagger и
//		протестировать с помощью Postman.
//
//		**Шаг 1**
//		Создать простое SpringBoot-приложение. В качестве группы и артефакта проекта можно использовать следующие
//		значения: groupId — ru.hogwarts, artefact — school.
//
//		**Шаг 2**
//		1. Создать каталоги model, service, controller в пакете ru.hogwarts.school. В model создать два класса:
//		Student, Faculty.
//		Класс **Student** имеет следующие поля: **Long id, String name, int age.**
//		Класс **Faculty** имеет следующие поля: **Long id, String name, String color.**
//
//		1. Добавить конструкторы к классам, с помощью которых можно проинициализировать все поля (создать объект класса
//		через new и передать в него все параметры).
//		2. Создать методы для получения и изменения переменных класса. Сами переменные должны быть private.
//
//		Если есть желание, можно добавить свои поля, которые могут быть вам необходимы.

//		**Шаг 3**
//		1. В каталоге **service** cоздать два класса сервисов для моделей: StudentService и FacultyService.
//		2. В каждом из них завести HashMap, в котором следует хранить модели. Например Map<Long, Student>.
//		3. Также создать счетчик идентификатора, который будет инкрементироваться при каждом добавлении нового объекта
//		модели в HashMap.
//		4. В каждом сервисе реализовать CRUD-методы для создания, чтения, изменения и удаления сущностей.

//		**Шаг 4**
//		1. В каталоге **controller** cоздать два класса контроллеров для сервисов: StudentController и FacultyController.
//		2. В них добавить RequestMapping (“student” для StudentController и “faculty” для FacultyController).
//		3. В каждом контроллере реализовать эндпоинты для создания, получения, изменения и удаления сущностей, используя
//		все правила формирования REST-запросов: GET-методы для получения данных, POST — для создания…
//
//		**Шаг 5**
//		1. Добавить фильтрацию студентов по возрасту.
//
//		Для этого в StudentController добавить эндпоинт, который принимает число (возраст — поле age) и возвращает
//		список студентов, у которых совпал возраст с переданным числом.
//
//		1. Добавить фильтрацию факультетов по цвету.
//
//		Для этого в FacultyController добавить эндпоинт, который принимает строку (цвет — поле color) и возвращает
//		список факультетов, у которых совпал цвет с переданной строкой.
//
//		**Шаг 6**
//		Добавить swagger к проекту. Для этого добавить зависимость к проекту.
//
//		**Шаг 7**
//		Установить Postman и вызвать все эндпоинты проекта, используя его.
