package dev.kinodesu.calculaconta.application.ports.input.mapper;

import dev.kinodesu.calculaconta.domain.model.Table;
import dev.kinodesu.calculaconta.domain.entity.TableRequestDTO;
import dev.kinodesu.calculaconta.domain.entity.TableResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TableMapper {

    @Mapping(target = "tableId", source = "tableId")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "clientQuantity", source = "clientQuantity")
    TableResponseDTO toResponse(Table clientOrder);

    @Mapping(target = "tableId", source = "tableId")
    @Mapping(target = "name", source = "tableName")
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "clientQuantity", source = "clientQuantity")
    @Mapping(target = "qrCode", ignore = true)
    Table toEntity(TableRequestDTO tableResponseDTO);
}
