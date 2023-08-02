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

//		 3.3. Введение в базы данных
//
//		Цель сегодняшней домашней работы — установить и настроить взаимодействие приложения с базой данных и изменить
//		логику в сервисных классах, чтобы все изменения сохранялись в БД.

//		**Шаг 1**
//		Установить БД PostgreSQL. Создать базу данных hogwarts. Создать пользователя student с паролем chocolatefrog.

//		**Шаг 2**
//		В application.properties приложения прописать следующие атрибуты:
//			- spring.datasource.url — путь до установленной БД;
//			- spring.datasource.username — имя пользователя для подключения, в нашем случае это student;
//			- spring.datasource.password — пароль пользователя: chocolatefrog;
//			- spring.jpa.hibernate.ddl = update.

//		**Шаг 3**
//		Изменить модели Student и Faculty. К каждому классу добавить аннотацию @Entity. А к полю id добавить две
//		аннотации: @Id и @GeneratedValue.
//		А также создать пакет repository, в котором будут находиться два интерфейса: StudentRepository и FacultyRepository.
//		Оба этих интерфейса наследуют JpaRepository. Для интерфейса StudentRepository требуется указать, что в
//		JpaRepository надо работать с моделью Student. Для FacultyRepository указать Faculty.
//
//		**Шаг 4**
//		В сервисах создать приватные поля репозиториев. Для StudentService создать StudentRepository.
//		Для FacultyService создать FacultyRepository. С помощью конструкторов подтягивать зависимости из контекста
//		спринга (@Autowire).
//		В сервисах удалить HashMap, который использовали для хранения данных, и удалить счетчик идентификатора.
//		Вместо них следует использовать функционал репозиториев.
//
//		**Шаг 5**
//		Проверить все CRUD-запросы через Postman.

