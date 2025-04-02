package dev.kinodesu.calculaconta.domain.usecase;

import dev.kinodesu.calculaconta.domain.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserUseCase {
    void saveNewUser(User user);

    List<User> getAllUsers();

    User getUserById(UUID id);
}
