package dev.kinodesu.calculaconta.domain.command.impl;

import dev.kinodesu.calculaconta.domain.command.BillingCommand;
import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.domain.usecase.UserUseCase;

import java.util.List;
import java.util.UUID;

public class BillingCommandImpl implements BillingCommand {

    private final UserUseCase userUseCase;

    public BillingCommandImpl(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Override
    public List<User> getAllUsersByRoom(String roomCode) {
        return userUseCase.getAllUsersByRoom(roomCode);
    }

    @Override
    public User getUserById(String user) {
        return userUseCase.getUserById(UUID.fromString(user));
    }
}
