package dev.kinodesu.calculaconta.infra.mapper;

import dev.kinodesu.calculaconta.domain.entity.User;
import dev.kinodesu.calculaconta.infra.repository.data.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserDataMapper {

    @Mapping(source = "userId",
            target = "userId")
    User toEntity(UserData userData);

    List<User> toEntity(List<UserData> userData);

    @Mapping(source = "userId",
            target = "userId")
    UserData toData(User user);

    List<UserData> toData(List<User> user);
}
