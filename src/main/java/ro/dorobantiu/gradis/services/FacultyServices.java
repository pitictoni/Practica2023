package ro.dorobantiu.gradis.services;

import com.moandjiezana.toml.Toml;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.dorobantiu.gradis.DTOs.FacultyDTO;
import ro.dorobantiu.gradis.entities.Faculty;
import ro.dorobantiu.gradis.helpers.ExcelUtil;
import ro.dorobantiu.gradis.repositories.FacultyRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

@Service
public class FacultyServices {

 //   Toml toml = new Toml().read(Excel);
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    Mapper mapper;

    public Collection<FacultyDTO> importFaculties(InputStream excelStream) {
        Collection<Faculty> faculties = getFaculties(excelStream);
        facultyRepository.saveAll(faculties);
        return faculties.stream().map(x -> mapper.toDTO(x)).toList();
    }

    public Collection<Faculty> getFaculties(InputStream excelStream) { // TODO add unique param??
        try {
            Workbook workbook = new XSSFWorkbook(excelStream);

            String facultySheet = "Foaie1";
            Sheet sheet = workbook.getSheet(facultySheet);
            Iterator<Row> rows = sheet.iterator();

            HashSet<Faculty> faculties = new HashSet<>();

            rows.next(); // skip header
            while (rows.hasNext()) {
                Row currentRow = rows.next();
                String facultyName = ExcelUtil.getCellData(currentRow, "DenumireFacultate");

                Faculty faculty = new Faculty();
                faculty.setName(facultyName);
                faculties.add(faculty);
            }
            return faculties;
        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    public Collection<FacultyDTO> getAllFaculties() {
        Collection<FacultyDTO> facultyDTOS = new ArrayList<>();
        facultyRepository.findAll().forEach(x -> facultyDTOS.add(new FacultyDTO(x.getId(),x.getName())));
        return facultyDTOS;
    }
}
