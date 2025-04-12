package dev.kinodesu.calculaconta.domain.command.impl;

import dev.kinodesu.calculaconta.domain.command.RoomUserCommand;
import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.domain.usecase.RoomUseCase;

public class RoomUserCommandImpl implements RoomUserCommand {

    private final RoomUseCase roomUseCase;

    public RoomUserCommandImpl(RoomUseCase roomUseCase) {
        this.roomUseCase = roomUseCase;
    }

    @Override
    public Room createRoom() {
        return roomUseCase.createRoom();
    }
}
