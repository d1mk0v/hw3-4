package ru.hogwaarts.school.services.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwaarts.school.models.Avatar;
import ru.hogwaarts.school.models.Student;
import ru.hogwaarts.school.repositories.AvatarRepository;
import ru.hogwaarts.school.repositories.StudentRepository;
import ru.hogwaarts.school.services.api.AvatarService;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;
    private final StudentRepository studentRepository;

    @Value("${path.to.avatars.folder}")
    private String avatarsDir;

    public AvatarServiceImpl(AvatarRepository avatarRepository, StudentRepository studentRepository) {
        this.avatarRepository = avatarRepository;
        this.studentRepository = studentRepository;
    }

    public AvatarRepository getAvatarRepository() {
        return avatarRepository;
    }

    public StudentRepository getStudentRepository() {
        return studentRepository;
    }

    @Override
    public void uploadAvatar(Long studentId, MultipartFile avatarFile) throws IOException {

        Student student = studentRepository.getReferenceById(studentId);

    }

    @Override
    public Avatar findAvatar(Long studentId) {
        return null;
    }
}
