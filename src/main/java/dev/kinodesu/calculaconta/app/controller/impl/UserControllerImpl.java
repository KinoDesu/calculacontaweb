package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.UserController;
import dev.kinodesu.calculaconta.app.dto.request.UserRequestDTO;
import dev.kinodesu.calculaconta.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<Object> saveNewUser(UserRequestDTO userRequestDTO) {
        userService.saveNewUser(userRequestDTO);
        return ResponseEntity.created(URI.create("")).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
}
