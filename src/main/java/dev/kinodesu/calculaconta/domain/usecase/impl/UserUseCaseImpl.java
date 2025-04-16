package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.UserDataProvider;
import dev.kinodesu.calculaconta.domain.command.RoomUserCommand;
import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.domain.usecase.UserUseCase;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;

@Slf4j
public class UserUseCaseImpl implements UserUseCase {

    private final UserDataProvider userDataProvider;
    private final RoomUserCommand roomUserCommand;

    public UserUseCaseImpl(UserDataProvider userDataProvider, RoomUserCommand roomUserCommand) {
        this.userDataProvider = userDataProvider;
        this.roomUserCommand = roomUserCommand;
    }

    @Override
    public User saveNewUser(User user) {
        log.info("save user");

        if (user.getRoom() == null) {
            Room newRoom = roomUserCommand.createRoom();
            user.setRoom(newRoom);
        }

        return userDataProvider.saveNewUser(user);
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

    @Override
    public List<User> getAllUsersByRoom(String roomCode) {
        return userDataProvider.getAllUsersByRoom(roomCode);
    }
}
