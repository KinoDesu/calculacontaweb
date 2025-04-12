package dev.kinodesu.calculaconta.app.controller.impl;

import dev.kinodesu.calculaconta.app.controller.UserController;
import dev.kinodesu.calculaconta.app.dto.request.UserRequestDTO;
import dev.kinodesu.calculaconta.app.service.UserService;
import dev.kinodesu.calculaconta.domain.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.NoSuchElementException;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/api/user")
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    @PostMapping
    public ResponseEntity<Object> saveNewUser(@RequestBody UserRequestDTO userRequestDTO) {
        try {
            User savedUser = userService.saveNewUser(userRequestDTO);

            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{userId}")
                    .buildAndExpand(savedUser.getUserId())
                    .toUri();

            return ResponseEntity.created(location).build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable String userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    @GetMapping(params = {"roomCode"})
    public ResponseEntity<Object> getAllUsersByRoom(@RequestParam String roomCode) {
        return ResponseEntity.ok(userService.getAllUsersByRoom(roomCode));
    }
}
