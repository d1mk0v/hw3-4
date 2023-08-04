package ru.hogwaarts.school.services.api;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwaarts.school.models.Avatar;

import java.io.IOException;

public interface AvatarService {

    void uploadAvatar(Long id, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long id);
}
