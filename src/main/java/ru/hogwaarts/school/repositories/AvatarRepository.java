package ru.hogwaarts.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hogwaarts.school.models.Avatar;

import java.util.Optional;

public interface AvatarRepository extends JpaRepository<Avatar, Long> {
    Optional<Avatar> findByStudentId(Long studentId);

}