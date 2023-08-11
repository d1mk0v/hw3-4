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

//		4.3. Миграции и индексы.
//
//		В этом уроке мы поработаем с индексами и настроим миграции.
//
//		Цель сегодняшней домашней работы — создать несколько миграций для добавления индексов. Настройку миграций
//		будем осуществлять с помощью Liquibase. В дальнейшем мы будем использовать Liquibase для всех операций с БД
//
//		**Шаг 1**
//		Добавить Liquibase к проекту. Создать файл для миграций с любым говорящим названием, которое относится к
//		текущему уроку. В качестве названия можно использовать, например: lesson-three, course-four-lesson-three,
//		index-practice и т.д.
//
//		Критерии оценки. Подключена зависимость liquibase-core. Создан пустой файл миграций.
//		Настроен файл changelog-master.yml.
//
//		**Шаг 2**
//		Добавить два индекса, используя миграции:
//			1) Индекс для поиска по имени студента.
//			2) Индекс для поиска по названию и цвету факультета.
//
//		Критерий оценки. В файле миграций созданы два changeset с SQL-командами для создания индексов.



