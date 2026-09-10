package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper;

import dev.kinodesu.calculaconta.domain.model.Table;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.TableData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = ClientDataMapper.class)
public interface TableDataMapper {

    @Mapping(source = "tableId",
            target = "tableId")
    @Mapping(source = "name",
            target = "name")
    @Mapping(source = "code",
            target = "code")
    @Mapping(source = "qrCode",
            target = "qrCode")
    @Mapping(source = "clientQuantity",
            target = "clientQuantity")
    Table toEntity(TableData userData);

    List<Table> toEntity(List<TableData> userData);

    @Mapping(source = "tableId",
            target = "tableId")
    @Mapping(source = "name",
            target = "name")
    @Mapping(source = "code",
            target = "code")
    @Mapping(source = "qrCode",
            target = "qrCode")
    @Mapping(source = "clientQuantity",
            target = "clientQuantity")
    TableData toData(Table user);

    List<TableData> toData(List<Table> user);

    @Mapping(target = "tableId", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "qrCode", ignore = true)
    @Mapping(target = "userDataList", ignore = true)
    @Mapping(target = "orderDataList", ignore = true)
    void updateDate(Table table, @MappingTarget TableData existingTable);
}
