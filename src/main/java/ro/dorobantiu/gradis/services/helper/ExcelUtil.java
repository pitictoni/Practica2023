package ro.dorobantiu.gradis.services.helper;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;

@Service
public class ExcelUtil {
    public String test() {
        String filePath = "";
        StringBuilder sb = new StringBuilder();
        int worksheetIndex = 0;
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
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
