package dev.kinodesu.calculaconta.domain.usecase.impl;

import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.domain.usecase.RoomUseCase;
import dev.kinodesu.calculaconta.infra.dataprovider.RoomDataProvider;
import org.apache.commons.lang3.RandomStringUtils;

public class RoomUseCaseImpl implements RoomUseCase {

    private final RoomDataProvider roomDataProvider;

    public RoomUseCaseImpl(RoomDataProvider roomDataProvider) {
        this.roomDataProvider = roomDataProvider;
    }

    @Override
    public Room createRoom() {

        Room room = Room.builder()
                .code(RandomStringUtils.randomAlphanumeric(6))
                .build();

        return roomDataProvider.create(room);
    }

    @Override
    public Room findByCode(String roomCode) {
        if(roomCode==null){
            return null;
        }
        return roomDataProvider.findByCode(roomCode);
    }

}
