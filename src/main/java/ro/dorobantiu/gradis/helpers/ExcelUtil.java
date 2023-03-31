package ro.dorobantiu.gradis.helpers;

import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExcelUtil {

    private static final Logger log = LoggerFactory.getLogger(ExcelUtil.class);

    public static String SHEET = "Foaie1";
    public static String JournalSHEET = "AIS.2021.cuartile.valori";
    public static String PaperSHEET = "I.1";

    public static String EXCELTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static Map<String, Integer> HEADER = new HashMap<>();

    public ExcelUtil() {
        setMetaInformation();
    }

    public static boolean hasExcelFormat(MultipartFile file) {
        return EXCELTYPE.equals(file.getContentType());
    }

    private void setMetaInformation() {
        // set HEADER
    }

    public static String getCellData(Row row, String columnName) {
        try {
            Cell cell = row.getCell(columnNumberFromName(columnName));
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING -> CellData = cell.getStringCellValue();
                case NUMERIC -> {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue());
                    }
                }
                case BOOLEAN -> CellData = Boolean.toString(cell.getBooleanCellValue());
                case BLANK -> CellData = "";
            }
            return CellData;
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return "";
        }
    }

    public String getCellData(Row row, int columnNumber) {
        try {
            Cell cell = row.getCell(columnNumber);
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING -> CellData = cell.getStringCellValue();
                case NUMERIC -> {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        CellData = String.valueOf(cell.getDateCellValue());
                    } else {
                        CellData = String.valueOf((float) cell.getNumericCellValue());
                    }
                }
                case BOOLEAN -> CellData = Boolean.toString(cell.getBooleanCellValue());
                case BLANK -> CellData = "";
            }
            return CellData;
        } catch (Exception e) {
            log.error(e.getStackTrace().toString());
            return "";
        }
    }


    private static int columnNumberFromName(String columnName) {
        // TODO use COLUMNS map
        return switch (columnName) {
            case "mail" -> 0;
            case "nume" -> 1;
            case "prenume" -> 2;
            case "Functie" -> 5;
            case "DenumireFacultate" -> 6;
            case "DenumireDepartament" -> 7;
            default -> 0;
        };
    }


}
