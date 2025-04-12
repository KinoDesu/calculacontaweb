package dev.kinodesu.calculaconta.infra.dataprovider;

import dev.kinodesu.calculaconta.domain.entity.Room;

public interface RoomDataProvider {
    Room create(Room room);

    Room findByCode(String roomCode);
}
