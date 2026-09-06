package dev.kinodesu.calculaconta.infra.dataprovider.impl;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dev.kinodesu.calculaconta.domain.TableDataProvider;
import dev.kinodesu.calculaconta.domain.entity.Table;
import dev.kinodesu.calculaconta.infra.mapper.TableDataMapper;
import dev.kinodesu.calculaconta.infra.repository.TableRepository;
import dev.kinodesu.calculaconta.infra.repository.data.TableData;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TableDataProviderImpl implements TableDataProvider {

    private final TableDataMapper tableDataMapper;
    private final TableRepository tableRepository;

    @Override
    public Table create(Table table, String redirectUrl) {

        if(table.getTableId()!=null){
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
    public Table findByCode(String tableCode) {
        Optional<TableData> tableDataOpt = tableRepository.findByCode(tableCode);
        return tableDataMapper.toEntity(tableDataOpt.orElseThrow());
    }

    @Override
    public byte[] generateQrCode(String redirectUrl) {
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

    @Override
    public byte[] getQrCodeByTableId(UUID tableId) {
        return tableRepository.findQrCodeByTableId(tableId);
    }

    @Override
    public Table findByid(UUID tableId) {
        return tableDataMapper.toEntity(tableRepository.findById(tableId).orElseThrow());
    }
}
