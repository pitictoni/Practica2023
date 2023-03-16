package ro.dorobantiu.gradis.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.dorobantiu.gradis.DTOs.UserDTO;
import ro.dorobantiu.gradis.entities.User;
import ro.dorobantiu.gradis.helpers.ExcelUtil;
import ro.dorobantiu.gradis.repositories.UserRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserServices {
    private static final Logger log = LoggerFactory.getLogger(UserServices.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    ExcelUtil excelUtil;

    @Autowired
    Mapper mapper;

    public Collection<UserDTO> importUsers(InputStream excelStream) {
        Collection<User> users = getUsers(excelStream);
        userRepository.saveAll(users);
        return users.stream().map(x -> mapper.toDTO(x)).toList();
    }

    private Collection<User> getUsers(InputStream excelStream) {
        try {
            Workbook workbook = new XSSFWorkbook(excelStream);
            Sheet sheet = workbook.getSheet(excelUtil.SHEET);
            Iterator<Row> rows = sheet.iterator();

            HashSet<User> users = new HashSet<>();

            Row currentRow = rows.next(); // skip header

            while (rows.hasNext()) {
                currentRow = rows.next();
                String userEmail = excelUtil.getCellData(currentRow, "mail");
                users.add(new User(userEmail, "123", true));
            }
            return users;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

