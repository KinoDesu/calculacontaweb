package dev.kinodesu.calculaconta.domain.command;

import dev.kinodesu.calculaconta.domain.entity.User;

import java.util.List;

public interface BillingCommand {

    List<User> getAllUsersByRoom(String roomCode);

    User getUserById(String user);
}
