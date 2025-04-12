package dev.kinodesu.calculaconta.app.service.impl;

import dev.kinodesu.calculaconta.app.service.RoomService;
import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.domain.usecase.RoomUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {

    private final RoomUseCase roomUseCase;

    @Override
    public Room findByCode(String roomCode) {
        return roomUseCase.findByCode(roomCode);
    }
}
