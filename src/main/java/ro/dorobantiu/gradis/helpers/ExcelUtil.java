package ro.dorobantiu.gradis.helpers;

import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExcelUtil {

    public static String SHEET = "Foaie1";
    //    @Autowired
//    FacultyServices facultyServices;
//    @Autowired
//    DepartmentServices departmentServices;
    public static String EXCELTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static Map<String, Integer> HEADER = new HashMap<>();

    public ExcelUtil() {
        setMetaInformation();
    }

    private void setMetaInformation() {
        // set HEADER

    }


    public static boolean hasExcelFormat(MultipartFile file) {
        return EXCELTYPE.equals(file.getContentType());
    }

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

//    public List<Author> importAuthors(InputStream excelStream){
//        int worksheetIndex=0;
//        List <Author> authorList = new ArrayList<Author>();
//
//        try (FileInputStream inputStream = new FileInputStream(filePath)) {
//            Workbook workbook = WorkbookFactory.create(inputStream);
//            Sheet sheet = workbook.getSheetAt(worksheetIndex);
//            for (Row row : sheet) {
//                Author author = new Author();
//                author.setName(row.getCell(1).getStringCellValue());
//
//              authorList.add(author);
//            }
//            return  authorList;
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public String getCellData(Row row, String columnName) {
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
            return "";
        }
    }

    private int columnNumberFromName(String columnName) {
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
