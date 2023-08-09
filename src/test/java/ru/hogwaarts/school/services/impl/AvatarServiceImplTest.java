package ru.hogwaarts.school.services.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwaarts.school.models.Avatar;
import ru.hogwaarts.school.repositories.AvatarRepository;
import ru.hogwaarts.school.repositories.StudentRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AvatarServiceImplTest {

    @Mock
    private AvatarRepository avatarRepository;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private MultipartFile avatarFile;

    private AvatarServiceImpl avatarService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        avatarService = new AvatarServiceImpl(avatarRepository, studentRepository);
        avatarService.setAvatarsDir("/path/to/avatars/folder");
    }

    @Test
    void findAvatarTest() {

        Long studentId = 1L;
        Avatar avatar = new Avatar();

        when(avatarRepository.findByStudentId(studentId)).thenReturn(Optional.of(avatar));

        Avatar result = avatarService.findAvatar(studentId);

        verify(avatarRepository, times(1)).findByStudentId(studentId);
        assertSame(avatar, result);
    }

    @Test
    void findAvatarIsEmptyTest() {

        Long studentId = 1L;

        when(avatarRepository.findByStudentId(studentId)).thenReturn(Optional.empty());

        Avatar result = avatarService.findAvatar(studentId);

        verify(avatarRepository, times(1)).findByStudentId(studentId);
        assertNotNull(result);
        assertNull(result.getStudent());
    }
}