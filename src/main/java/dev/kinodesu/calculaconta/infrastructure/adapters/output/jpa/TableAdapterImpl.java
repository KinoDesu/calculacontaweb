package dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dev.kinodesu.calculaconta.application.ports.output.TableOutputPort;
import dev.kinodesu.calculaconta.domain.model.Table;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.data.TableData;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.mapper.TableDataMapper;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.ClientOrderRepository;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.OrderRepository;
import dev.kinodesu.calculaconta.infrastructure.adapters.output.jpa.repository.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableAdapterImpl implements TableOutputPort {

    private final OrderRepository orderRepository;
    private final TableRepository tableRepository;
    private final TableDataMapper tableDataMapper;
    private final ClientOrderRepository clientOrderRepository;

    @Override
    @Transactional
    public void deleteAllOrdersByTableId(UUID tableId) {
        clientOrderRepository.deleteAllByTableId(tableId);
        orderRepository.deleteAllByTableId(tableId);
    }

    @Override
    public Table createOrUpdateTable(Table table, URI redirectUrl) {
        if (table.getTableId() != null) {
            TableData existingTable = tableRepository.findById(table.getTableId()).orElseThrow();
            tableDataMapper.updateDate(table, existingTable);

            return tableDataMapper.toEntity(tableRepository.save(existingTable));
        }

        String tableCode = RandomStringUtils.randomAlphanumeric(5);

        Table newTable = Table.builder()
                .tableId(UUID.randomUUID())
                .name(table.getName())
                .clientQuantity(table.getClientQuantity())
                .qrCode(generateQrCode(String.format("%s/table/%s", redirectUrl, tableCode)))
                .code(tableCode)
                .build();

        TableData tableData = tableDataMapper.toData(newTable);
        return tableDataMapper.toEntity(tableRepository.save(tableData));
    }

    @Override
    public Table getTableById(UUID tableId) {
        return tableDataMapper.toEntity(tableRepository.findById(tableId).orElseThrow(() -> new EntityNotFoundException(String.format("Mesa %s não encontrada", tableId))));
    }

    @Override
    public Table getTableByCode(String tableCode) {
        Table table = tableDataMapper.toEntity(tableRepository.findByCode(tableCode).orElseThrow(() -> new EntityNotFoundException(String.format("Mesa %s não encontrada", tableCode))));
        if (!table.getCode().equals(tableCode)) {
            throw new EntityNotFoundException(String.format("Mesa %s não encontrada", tableCode));
        }
        return table;
    }

    private byte[] generateQrCode(String redirectUrl) {
        try {
            QRCodeWriter qrCodeWriter = new QRCodeWriter();

            BitMatrix bitMatrix = qrCodeWriter.encode(redirectUrl, BarcodeFormat.QR_CODE, 250, 250);

            ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
            MatrixToImageConfig con = new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF);

            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream, con);
            return pngOutputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
