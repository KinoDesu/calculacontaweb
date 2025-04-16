package dev.kinodesu.calculaconta.domain;

import dev.kinodesu.calculaconta.domain.entity.Room;

public interface RoomDataProvider {
    Room create(Room room);

    Room findByCode(String roomCode);
}
