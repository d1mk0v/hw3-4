package ru.hogwaarts.school;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition
public class SchoolApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolApplication.class, args);
	}

}

//		 3.6. Тестирование веб-приложений в Spring Boot
//
//		Цель сегодняшней домашней работы — научиться писать тесты для приложений Spring Boot двумя способами:
//		используя TestRestTemplate и используя WebMvcTest.
//
//		Задание 1
//
//		**Шаг 1**
//		Создать класс для тестирования в пакете test. Создать по одному тесту на каждый эндпоинт контроллера
//		StudentController, используя TestRestTemplate.
//
//		**Шаг 2**
//		Создать еще один класс для тестирования в пакете test. Создать по одному тесту на каждый эндпоинт контроллера
//		FacultyController, используя TestRestTemplate.
//
//		## Задание 2
//
//		**Шаг 1**
//		Создать класс для тестирования в пакете test. Создать по одному тесту на каждый эндпоинт контроллера
//		StudentController, используя WebMvcTest.
//
//		**Шаг 2**
//		Создать еще один класс для тестирования в пакете test. Создать по одному тесту на каждый эндпоинт контроллера
//		FacultyController, используя WebMvcTest.
//


