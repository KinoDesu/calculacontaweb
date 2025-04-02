package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.domain.usecase.UserUseCase;
import dev.kinodesu.calculaconta.infra.dataprovider.UserDataProvider;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class UserUseCaseImpl implements UserUseCase {

    private final UserDataProvider userDataProvider;

    public UserUseCaseImpl(UserDataProvider userDataProvider) {
        this.userDataProvider = userDataProvider;
    }

    @Override
    public void saveNewUser(User user) {
        log.info("save user");
        userDataProvider.saveNewUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        log.info("get all user");
        return userDataProvider.getAllUsers();
    }

    @Override
    public User getUserById(UUID id) {
        return userDataProvider.getUserById(id.toString());
    }
}
