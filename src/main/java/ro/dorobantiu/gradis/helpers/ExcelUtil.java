package ro.dorobantiu.gradis.helpers;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ro.dorobantiu.gradis.entities.Author;
import ro.dorobantiu.gradis.entities.Department;
import ro.dorobantiu.gradis.entities.Faculty;
import ro.dorobantiu.gradis.services.DepartmentServices;
import ro.dorobantiu.gradis.services.FacultyServices;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ExcelUtil {

    public static final String SHEET = "Foaie1";
    //    @Autowired
//    FacultyServices facultyServices;
//    @Autowired
//    DepartmentServices departmentServices;
    public static String EXCELTYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "mail", "nume", "prenume", "stare", "Categorie", "Functie", "DenumireFacultate", "DenumireDepartament" };


    static private Map<String, Integer> COLUMNS = new HashMap<>();
    public ExcelUtil() {
        setMetaInformation();
    }

    private void setMetaInformation() {
        // TODO get header in a COLUMNS map
    }


    public static boolean hasExcelFormat(MultipartFile file) {

        if (!EXCELTYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
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

    public String getCellData(Row row, String columnName) throws Exception{
        try{
            Cell cell = row.getCell(columnNumberFromName(columnName));
            String CellData = null;
            switch (cell.getCellType()){
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell))
                    {
                        CellData = String.valueOf(cell.getDateCellValue());
                    }
                    else
                    {
                        CellData = String.valueOf((long)cell.getNumericCellValue());
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        }catch (Exception e){
            return"";
        }
    }

    private int columnNumberFromName(String columnName) {
        // TODO use COLUMNS map
        switch (columnName){
            case "DenumireFacultate": return 6;
            case "DenumireDepartament": return 7;

            default: return 0;
        }
    }



    public String saveToTempExcel(MultipartFile file) {
        return "";
    }


}
