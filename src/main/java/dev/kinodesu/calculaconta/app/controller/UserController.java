package dev.kinodesu.calculaconta.app.controller;

import dev.kinodesu.calculaconta.app.dto.request.UserRequestDTO;
import org.springframework.http.ResponseEntity;

public interface UserController {

    ResponseEntity<Object> saveNewUser(UserRequestDTO user);

    ResponseEntity<Object> getUserById(String userId);

    ResponseEntity<Object> getAllUsers();

    ResponseEntity<Object> getAllUsersByRoom(String roomCode);
}
