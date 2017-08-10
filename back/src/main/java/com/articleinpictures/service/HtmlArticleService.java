package com.articleinpictures.service;

import com.articleinpictures.domain.Article;
import com.articleinpictures.exception.InvalidURLException;
import java.io.IOException;
import java.net.MalformedURLException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * An {@link ArticleService} that uses an {@link HttpService} to fetch an HTTP page and uses Jsoup to get its body
 * content, and then calls {@link PlainTextArticleService} to convert the text into an {@link Article}.
 * 
 * @author Teemu Hirvonen
 * @see HttpService
 * @see PlainTextArticleService
 */
@Service("html")
public class HtmlArticleService implements ArticleService<String> {
    @Autowired
    @Qualifier("plaintext")
    private ArticleService<String> plainTextService;
    
    @Autowired
    @Qualifier("jsoup")
    private HttpService httpService;
    
    @Override
    public Article convert(final String url) {
        if (null == url) throw new NullPointerException();
        
        try {
            final String content = httpService.getContent(url);

            final Document doc = Jsoup.parse(content);

            return plainTextService.convert(doc.body().text());
        } catch (final IllegalArgumentException|MalformedURLException ex) {
            throw new InvalidURLException("Invalid URL");
        } catch (final IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
