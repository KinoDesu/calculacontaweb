package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.dto.request.UserRequestDTO;
import dev.kinodesu.calculaconta.app.service.UserService;
import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.domain.usecase.RoomUseCase;
import dev.kinodesu.calculaconta.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserUseCase userUseCase;
    private final RoomUseCase roomUseCase;

    @Override
    public User saveNewUser(UserRequestDTO userRequestDTO) {

        Room room = roomUseCase.findByCode(userRequestDTO.getRoomCode());

        User user = User.builder()
                .name(userRequestDTO.getName())
                .room(room)
                .totalAmount(0).build();

        return userUseCase.saveNewUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userUseCase.getAllUsers();
    }

    @Override
    public List<User> getAllUsersByRoom(String roomCode) {
        return userUseCase.getAllUsersByRoom(roomCode);
    }

    @Override
    public User getUserById(String userId) {
        return userUseCase.getUserById(UUID.fromString(userId));
    }
}
