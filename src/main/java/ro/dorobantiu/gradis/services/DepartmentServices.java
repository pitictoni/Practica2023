package ro.dorobantiu.gradis.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.dorobantiu.gradis.configuration.InitialDatabaseConfiguration;
import ro.dorobantiu.gradis.entities.Department;
import ro.dorobantiu.gradis.entities.Faculty;
import ro.dorobantiu.gradis.helpers.ExcelUtil;
import ro.dorobantiu.gradis.repositories.DepartmentRepository;
import ro.dorobantiu.gradis.repositories.FacultyRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
public class DepartmentServices {

    private static final Logger log = LoggerFactory.getLogger(DepartmentServices.class);

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    FacultyServices facultyServices;
    @Autowired
    ExcelUtil excelUtil;

    public List<Department> importDepartments(InputStream excelStream) {
        // facultyServices.importFaculties(excelStream); // TODO check if already imported?
        List <Department> departments = getDepartments(excelStream);
       // departmentRepository.saveAll(departments);
        return departments;
    }

    public List<Department> getDepartments(InputStream excelStream) {
        try{
            Workbook workbook = new XSSFWorkbook(excelStream);
            Sheet sheet = workbook.getSheet(excelUtil.SHEET);
            Iterator<Row> rows = sheet.iterator();

            HashSet<Department> departments = new HashSet<>();

            Row currentRow = rows.next(); // skip header

            while ( rows.hasNext() ) {
                currentRow = rows.next();

                Department department = new Department();
                String departmentName = excelUtil.getCellData(currentRow,"DenumireDepartament");
                department.setName(departmentName);
                String facultyName =  excelUtil.getCellData(currentRow,"DenumireFacultate");
                department.setFaculty(facultyServices.getByName(facultyName));
                departments.add(department);
            }
            log.info(departments.toString());
            return departments.stream().toList();
        }
        catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
