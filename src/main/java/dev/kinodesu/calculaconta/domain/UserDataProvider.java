package dev.kinodesu.calculaconta.domain;

import dev.kinodesu.calculaconta.domain.entity.User;

import java.util.List;

public interface UserDataProvider {
    User saveNewUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);

    void updateTotalAmount(String id, double totalAmount);

    List<User> getAllUsersByRoom(String roomCode);

    void updateUser(User user);
}
