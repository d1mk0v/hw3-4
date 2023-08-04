package ru.hogwaarts.school.controllers;

import org.springframework.web.bind.annotation.RestController;
import ru.hogwaarts.school.services.api.AvatarService;

@RestController
public class AvatarController {

    private final AvatarService avatarService;

    public AvatarController(AvatarService avatarService) {
        this.avatarService = avatarService;
    }
}
