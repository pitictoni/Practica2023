package ro.dorobantiu.gradis.services.helper;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ExcelUtil {
    public String test(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int worksheetIndex = 0;
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(worksheetIndex);
            for (Row row : sheet) {
                for (int cellIndex = 0; cellIndex < 10; cellIndex++) {
                    Cell cell = row.getCell(cellIndex);
                    if (cell != null) {
                        sb.append("\n");
                        sb.append(cell.getStringCellValue());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
