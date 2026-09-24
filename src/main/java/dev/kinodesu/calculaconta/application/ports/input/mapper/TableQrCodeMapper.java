package dev.kinodesu.calculaconta.application.ports.input.mapper;

import dev.kinodesu.calculaconta.domain.entity.TableQrCodeResponseDTO;
import dev.kinodesu.calculaconta.domain.model.Table;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TableQrCodeMapper {
    @Mapping(target = "name", source = "name")
    @Mapping(target = "code", source = "code")
    @Mapping(target = "qrCode", source = "qrCode")
    TableQrCodeResponseDTO toResponse(Table clientOrder);
}
