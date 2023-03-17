package ro.dorobantiu.gradis.helpers;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class HtmlUtils {
    public String htmlFromURL(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            String parseLine;
            URL URL = new URL(url);
            BufferedReader br = new BufferedReader(new InputStreamReader(URL.openStream()));

            while ((parseLine = br.readLine()) != null) {
                sb.append(parseLine);
                sb.append("\n");
            }
            br.close();
        } catch (MalformedURLException me) {
            System.out.println(me);

        } catch (IOException ioe) {
            System.out.println(ioe);
        }

        return sb.toString();
    }
}
