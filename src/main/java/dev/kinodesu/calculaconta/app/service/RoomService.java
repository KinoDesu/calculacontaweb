package dev.kinodesu.calculaconta.app.service;

import dev.kinodesu.calculaconta.domain.entity.Room;

public interface RoomService {
    Room findByCode(String roomCode);
}
