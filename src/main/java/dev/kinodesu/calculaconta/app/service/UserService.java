package dev.kinodesu.calculaconta.app.service;

import dev.kinodesu.calculaconta.app.dto.request.UserRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.User;

import java.util.List;

public interface UserService {
    void saveNewUser(UserRequestDTO userRequestDTO);

    List<User> getAllUsers();
}
