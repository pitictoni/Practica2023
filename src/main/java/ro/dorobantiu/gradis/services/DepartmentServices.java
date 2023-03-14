package ro.dorobantiu.gradis.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.dorobantiu.gradis.DTOs.DepartmentDTO;
import ro.dorobantiu.gradis.entities.Department;
import ro.dorobantiu.gradis.helpers.ExcelUtil;
import ro.dorobantiu.gradis.repositories.DepartmentRepository;
import ro.dorobantiu.gradis.repositories.FacultyRepository;

import java.io.InputStream;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class DepartmentServices {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    ExcelUtil excelUtil;

    @Autowired
    Mapper mapper;

    public List<DepartmentDTO> importDepartments(InputStream excelStream) {
        List<Department> departments = getDepartments(excelStream);
        departmentRepository.saveAll(departments);
        return departments.stream().map(x -> mapper.toDTO(x)).collect(toList());
    }

    public List<Department> getDepartments(InputStream excelStream) {
        try {
            Workbook workbook = new XSSFWorkbook(excelStream);
            Sheet sheet = workbook.getSheet(ExcelUtil.SHEET);
            Iterator<Row> rows = sheet.iterator();

            HashSet<Department> departments = new HashSet<>();

            Row currentRow = rows.next(); // skip header

            while (rows.hasNext()) {
                currentRow = rows.next();

                Department department = new Department();
                String departmentName = excelUtil.getCellData(currentRow, "DenumireDepartament");
                department.setName(departmentName);
                String facultyName = excelUtil.getCellData(currentRow, "DenumireFacultate");
                department.setFaculty(facultyRepository.findByName(facultyName));
                departments.add(department);
            }
            return departments.stream().toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Department getDepartmentByName(String departmentName) {
        return departmentRepository.findByName(departmentName);
    }
}
