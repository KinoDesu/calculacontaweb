package dev.kinodesu.calculaconta.infra.mapper;

import dev.kinodesu.calculaconta.domain.entity.Room;
import dev.kinodesu.calculaconta.infra.repository.data.RoomData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UserDataMapper.class)
public interface RoomDataMapper {

    @Mapping(source = "roomId",
            target = "roomId")
    Room toEntity(RoomData userData);

    List<Room> toEntity(List<RoomData> userData);

    @Mapping(source = "roomId",
            target = "roomId")
    RoomData toData(Room user);

    List<RoomData> toData(List<Room> user);
}
