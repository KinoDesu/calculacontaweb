package dev.kinodesu.calculaconta.domain.usecase;

import dev.kinodesu.calculaconta.domain.entity.Room;

public interface RoomUseCase {
    Room createRoom();

    Room findByCode(String roomCode);
}
