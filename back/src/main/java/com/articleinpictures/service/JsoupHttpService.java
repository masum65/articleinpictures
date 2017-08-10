package com.articleinpictures.service;

import java.io.IOException;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

/**
 * This is the default implementation of {@code HttpService}, it uses Jsoup to get the HTML document.
 * @author Teemu Hirvonen
 */
@Service("jsoup")
public class JsoupHttpService implements HttpService {
    @Override
    public String getContent(final String url) throws IOException {
        return Jsoup.connect(url).get().html();
    }
}
