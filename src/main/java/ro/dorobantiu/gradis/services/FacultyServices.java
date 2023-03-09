package ro.dorobantiu.gradis.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.dorobantiu.gradis.entities.Faculty;
import ro.dorobantiu.gradis.helpers.ExcelUtil;
import ro.dorobantiu.gradis.repositories.FacultyRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
public class FacultyServices {

    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    ExcelUtil excelUtil;

//    public FacultyServices(FacultyRepository facultyRepository) {
//        this.facultyRepository = facultyRepository;
//    }
//
    public Faculty getByName(String facultyName) {
        return facultyRepository.findByName(facultyName);
    }


    public List<Faculty>  importFaculties(InputStream excelStream){
        List <Faculty> faculties = getFaculties(excelStream);
        facultyRepository.saveAll(faculties);
        return faculties;
    }

    public List<Faculty> getFaculties(InputStream excelStream){ // TODO add unique param??
        try{
            Workbook workbook = new XSSFWorkbook(excelStream);
            Sheet sheet = workbook.getSheet(excelUtil.SHEET);
            Iterator<Row> rows = sheet.iterator();

            HashSet <Faculty> faculties = new HashSet<>();

            Row currentRow = rows.next(); // skip header

            while ( rows.hasNext() ) {
                currentRow = rows.next();

                Faculty faculty = new Faculty();
                String facultyName = excelUtil.getCellData(currentRow,"DenumireFacultate");
                faculty.setName(facultyName);
                faculties.add(faculty);
            }
            return faculties.stream().toList();
        }
        catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
