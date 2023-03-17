package ro.dorobantiu.gradis.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.dorobantiu.gradis.DTOs.JournalDTO;
import ro.dorobantiu.gradis.entities.Author;
import ro.dorobantiu.gradis.entities.Faculty;
import ro.dorobantiu.gradis.entities.Journal;
import ro.dorobantiu.gradis.helpers.ExcelUtil;
import ro.dorobantiu.gradis.repositories.JournalRepository;

import java.io.InputStream;
import java.util.*;

@Service
public class JournalServices {

    @Autowired
    JournalRepository journalRepository;
    @Autowired
    ExcelUtil excelUtil;
    @Autowired
    Mapper mapper;

    public Collection<JournalDTO> importJournals(InputStream excelStream) {
        Collection<Journal> journals = getJournals(excelStream);
        journalRepository.saveAll(journals);
        int numberOfReturnedJournals = 50;
        return journals.stream().limit(numberOfReturnedJournals).map(x -> mapper.toDTO(x)).toList();
    }

    public Collection<Journal> getJournals(InputStream excelStream) { // TODO add unique param??
        try {
            Workbook workbook = new XSSFWorkbook(excelStream);
            Sheet sheet = workbook.getSheet(ExcelUtil.JournalSHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Journal> journals = new ArrayList<>();

            int numberOfRows = 0;
            int maxNumberOfRows = 1000000;
            rows.next(); // skip header
            while (rows.hasNext() && numberOfRows < maxNumberOfRows) {

                Row currentRow = rows.next();
                String title = excelUtil.getCellData(currentRow, 0);
                String ISSN = excelUtil.getCellData(currentRow, 1);
                if (ISSN.equals("N/A")) ISSN = "0000-0000";
                String eISSN = excelUtil.getCellData(currentRow, 2);
                if (eISSN.equals("N/A")) eISSN = "0000-0000";

                String impactFactorString = excelUtil.getCellData(currentRow, 3);
                if (impactFactorString.equals("N/A")) impactFactorString = "0";
                float impactFactor = Float.parseFloat(impactFactorString);

                String indexing = excelUtil.getCellData(currentRow, 4);
                String woSCathegory = excelUtil.getCellData(currentRow, 5);
                String quartil = excelUtil.getCellData(currentRow, 6);

                // TODO keep only the highest quartile journal if duplicate name/ISSN/eISSN
                String finalISSN = ISSN;
                journals.add(new Journal(title, impactFactor, indexing, woSCathegory, quartil, ISSN, eISSN));
            }

            return uniqueJournals(journals);

        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }

    private Collection<Journal> uniqueJournals(List<Journal> journals) {
        Map<String, Journal> journalMap = new HashMap<>();

        for (Journal journal : journals) {
            if (!journalMap.containsKey(journal.getTitle()) || journalMap.get(journal.getTitle()).getQuartil().compareTo(journal.getQuartil()) < 0)
                journalMap.put(journal.getTitle(), journal);
        }

        return journalMap.values();
    }
}
