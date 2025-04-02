package dev.kinodesu.calculaconta.infra.dataprovider;

import dev.kinodesu.calculaconta.domain.entity.User;

import java.util.List;

public interface UserDataProvider {
    void saveNewUser(User user);

    List<User> getAllUsers();

    User getUserById(String id);

    void updateTotalAmount(String id, double totalAmount);
}
