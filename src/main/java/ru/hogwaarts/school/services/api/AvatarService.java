package ru.hogwaarts.school.services.api;

import org.springframework.web.multipart.MultipartFile;
import ru.hogwaarts.school.models.Avatar;

import java.io.IOException;
import java.util.List;

public interface AvatarService {

    void uploadAvatar(Long id, MultipartFile avatarFile) throws IOException;

    Avatar findAvatar(Long id);

    List<Avatar> getAllAvatars(Integer pageNumber, Integer pageSize);

    Long calculation();
}
