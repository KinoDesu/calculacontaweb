package dev.kinodesu.calculaconta.infra.dataprovider.impl;

import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.infra.dataprovider.RoomDataProvider;
import dev.kinodesu.calculaconta.infra.mapper.RoomDataMapper;
import dev.kinodesu.calculaconta.infra.repository.RoomRepository;
import dev.kinodesu.calculaconta.infra.repository.data.RoomData;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RoomDataProviderImpl implements RoomDataProvider {

    private final RoomDataMapper roomDataMapper;
    private final RoomRepository roomRepository;

    @Override
    public Room create(Room room) {
        RoomData roomData = roomDataMapper.toData(room);
        RoomData newRoom = roomRepository.save(roomData);
        return roomDataMapper.toEntity(newRoom);
    }

    @Override
    public Room findByCode(String roomCode) {
        Optional<RoomData> roomDataOpt = roomRepository.findByCode(roomCode);
        return roomDataMapper.toEntity(roomDataOpt.orElseThrow());
    }
}
