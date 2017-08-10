package com.articleinpictures.service;

import com.articleinpictures.Application;
import com.articleinpictures.domain.Article;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Teemu Hirvonen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PlainTextArticleServiceTest {
    @Autowired
    @Qualifier("plaintext")
    private ArticleService<String> service;
    
    @Test(expected = NullPointerException.class)
    public void testConvert_nullString_throwsNullPointerException() {
        service.convert(null);
    }
    
    @Test
    public void testConvert_emptyString() {
        final Article article = service.convert("");
        
        assertTrue(article.keywords(5).isEmpty());
    }
    
    @Test
    public void testConvert_validString_returnsKeywordsInCorrectOrder() {
        final Article article = service.convert("mary had one little lamb which mary had had loved");
        
        final List<String> keywords = article.keywords(5);
        
        assertEquals("had", keywords.get(0));
        assertEquals("mary", keywords.get(1));
    }
}
