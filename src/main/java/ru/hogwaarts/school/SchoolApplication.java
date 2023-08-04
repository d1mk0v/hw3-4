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

//		 3.5. Потоки данных. Работа с файлами
//
//		**Шаг 1**
//		Создать модель Avatar. В ней будем хранить аватарки студентов. В модель добавить следующие поля:
//		Long id, String filePath, long fileSize, String mediaType, byte[] data, Student student.
//
//		**Критерии оценки:**
//		Создана модель с необходимыми полями. У модели есть аннотации: @Entity, @Id, @GeneratedValue.
//
//		**Шаг 2**
//		Настроить связь OneToOne между моделями Student и Avatar. Для этого к полю student в модели Avatar добавить
//		аннотацию @OneToOne. Добавить контроллер, сервис и репозиторий для работы с моделью Avatar.
//
//		**Критерии оценки:**
//		Аннотация @OneToOne добавлена. После запуска приложения в таблице Avatar должна появиться колонка student_id.
//		Созданы контроллер, сервис и репозиторий для модели Avatar.
//
//		**Шаг 3**
//		Добавить три эндпоинта. Первый для загрузки картинки. При загрузке должно происходить сохранение данных как в БД,
//		так и на локальный диск. Второй эндпоинт должен возвращать картинку из БД. Третий должен возвращать картинку
//		из директории.
//
//		**Критерии оценки:**
//		Созданы три эндпоинта. Первый позволяет загружать картинки как в БД, так и на локальный диск.
//		Остальные два эндпоинта позволяют получать данные из БД или директории, где они хранятся.
//		Все три эндпоинта должны работать корректно в Swagger Ui.

