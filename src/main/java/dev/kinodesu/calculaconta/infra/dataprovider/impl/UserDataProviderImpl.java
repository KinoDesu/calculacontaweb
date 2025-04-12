package dev.kinodesu.calculaconta.infra.dataprovider.impl;

import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.infra.dataprovider.UserDataProvider;
import dev.kinodesu.calculaconta.infra.mapper.UserDataMapper;
import dev.kinodesu.calculaconta.infra.repository.UserRepository;
import dev.kinodesu.calculaconta.infra.repository.data.UserData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserDataProviderImpl implements UserDataProvider {

    private final UserRepository userRepository;
    private final UserDataMapper userDataMapper;

    @Override
    public User saveNewUser(User user) {
        UserData userData = userDataMapper.toData(user);
        return userDataMapper.toEntity(userRepository.save(userData));
    }

    @Override
    public List<User> getAllUsers() {
        List<UserData> userDataList = userRepository.findAll();
        return userDataMapper.toEntity(userDataList);
    }

    @Override
    public User getUserById(String id) {
        UserData userData = userRepository.findById(id).orElseThrow();
        return userDataMapper.toEntity(userData);
    }

    @Override
    public void updateTotalAmount(String id, double totalAmount) {
        userRepository.updateTotalAmountById(id, totalAmount);
    }

    @Override
    public List<User> getAllUsersByRoom(String roomCode) {
        return userDataMapper.toEntity(userRepository.findAllByRoomCode(roomCode));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(userDataMapper.toData(user));
    }
}
