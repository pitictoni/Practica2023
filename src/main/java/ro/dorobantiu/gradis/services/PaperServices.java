package ro.dorobantiu.gradis.services;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.dorobantiu.gradis.DTOs.PaperDTO;
import ro.dorobantiu.gradis.entities.Author;
import ro.dorobantiu.gradis.entities.Journal;
import ro.dorobantiu.gradis.entities.Paper;
import ro.dorobantiu.gradis.helpers.ExcelUtil;
import ro.dorobantiu.gradis.helpers.HtmlUtils;
import ro.dorobantiu.gradis.repositories.AuthorRepository;
import ro.dorobantiu.gradis.repositories.PaperRepository;

import java.io.InputStream;
import java.util.*;

@Service
public class PaperServices {

    private static final Logger log = LoggerFactory.getLogger(PaperServices.class);
    @Autowired
    HtmlUtils htmlUtils;
    @Autowired
    ExcelUtil excelUtil;
    @Autowired
    PaperRepository paperRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    Mapper mapper;

    private String getTitleFromHtml(String html) {
        Document doc = Jsoup.parse(html);
        Elements metaTags = doc.getElementsByTag("meta");
        for (Element element : metaTags) {
            if (element.attr("name").equals("title")) {
                return element.attr("content");
            }
        }
        return "No valid title found";
    }

        private Collection<Author> getAuthorsFromHtml(String html) {
        Collection <Author> authors = new ArrayList<>();
        Collection<String> authorNames = new ArrayList<>();

        Document doc = Jsoup.parse(html);
        Elements metaTags = doc.getElementsByTag("meta");

        for (Element element : metaTags) {
            if (element.attr("name").equals("dc.creator")) {
                authorNames.add( element.attr("content"));
            }
        }

        for(String authorName:authorNames){
            //authors.add(authorRepository.getAuthorByName(authorName));
            log.info(authorName);
            authors.add(authorRepository.findOneByName("FLOREA ADRIAN"));
        }

        return authors;
    }
    public PaperDTO importPaperFromURL(String url) {
        Paper paper = new Paper();
        String html = htmlUtils.htmlFromURL(url);
        paper.setTitle(getTitleFromHtml(html));
        paper.setAuthors(getAuthorsFromHtml(html));
       // log.info(paper.toString());
        paperRepository.save(paper);
        return new PaperDTO(paper.getId(), paper.getTitle(), paper.getRawAuthorList());
    }

    public Collection<PaperDTO> importPapers(InputStream excelStream) {
        Collection<Paper> papers = getPapers(excelStream);
       // paperRepository.saveAll(papers);
        return papers.stream().map(x -> mapper.toDTO(x)).toList();
    }

    private Collection<Paper> getPapers(InputStream excelStream) {
        try {
            Workbook workbook = new XSSFWorkbook(excelStream);
            Sheet sheet = workbook.getSheet(ExcelUtil.PaperSHEET);
            Iterator<Row> rows = sheet.iterator();

            List<Paper> papers = new ArrayList<>();

            int numberOfRows = 0;
            int maxNumberOfRows = 1000000;
            int rowsToSkip = 9;
            for(int i=1;i<=rowsToSkip;i++)
                rows.next(); // skip instructions and header
            while (rows.hasNext() && numberOfRows < maxNumberOfRows) {

                Row currentRow = rows.next();
                String title = excelUtil.getCellData(currentRow, 0);
                String rawAuthorList = excelUtil.getCellData(currentRow, 1);
                String rawJournalTitle = excelUtil.getCellData(currentRow, 3);
                if (title.equals("")) break;
                papers.add(new Paper(title,rawAuthorList,rawJournalTitle));
            }

            return papers;

        } catch (Exception e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
