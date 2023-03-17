package ro.dorobantiu.gradis.services;

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
import ro.dorobantiu.gradis.entities.Paper;
import ro.dorobantiu.gradis.helpers.HtmlUtils;
import ro.dorobantiu.gradis.repositories.AuthorRepository;
import ro.dorobantiu.gradis.repositories.PaperRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class PaperServices {

    private static final Logger log = LoggerFactory.getLogger(PaperServices.class);
    @Autowired
    HtmlUtils htmlUtils;
    @Autowired
    PaperRepository paperRepository;

    @Autowired
    AuthorRepository authorRepository;

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
        return paper.toPaperDTO();
    }

}
