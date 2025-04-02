package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.dto.request.UserRequestDTO;
import dev.kinodesu.calculaconta.app.service.UserService;
import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.domain.usecase.UserUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserUseCase userUseCase;

    @Override
    public void saveNewUser(UserRequestDTO userRequestDTO) {
        User user = User.builder()
                .name(userRequestDTO.getName())
                .totalAmount(0).build();

        userUseCase.saveNewUser(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userUseCase.getAllUsers();
    }
}
