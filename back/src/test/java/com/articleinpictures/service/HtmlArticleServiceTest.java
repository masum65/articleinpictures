package com.articleinpictures.service;

import com.articleinpictures.Application;
import com.articleinpictures.TestHelper;
import com.articleinpictures.domain.Article;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Teemu Hirvonen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class HtmlArticleServiceTest {
    @Mock
    private HttpService httpService;

    @Autowired
    @InjectMocks
    @Qualifier("html")
    private ArticleService<String> service;
    
    @Test(expected = NullPointerException.class)
    public void testConvert_nullUrl_throwsNullPointerException() {
        service.convert(null);
    }
    
    @Test
    public void testConvert_emptyHtmlBody_returnsEmptyList() throws IOException {
        when(httpService.getContent(anyString()))
            .thenReturn("");
        
        assertTrue(service.convert("").keywords(5).isEmpty());
    }
    
    @Test
    public void testConvert_validGeneratedHtmlContent_returnsCorrectKeywords() throws IOException {
        final String httpResponse = TestHelper.readFileAsString(new File("src/test/resources/html/lorem-ipsum.html"));
        
        when(httpService.getContent(anyString()))
            .thenReturn(httpResponse);
        
        final Article a = service.convert("lorem ipsum");
        final List<String> keywords = a.keywords(5);
        
        assertEquals(5, keywords.size());
        assertEquals("nulla", keywords.get(0));
        assertEquals("vitae", keywords.get(1));
        assertEquals("neque", keywords.get(2));
        assertEquals("sit", keywords.get(3));
        assertEquals("amet", keywords.get(4));
    }
    
    @Test
    public void testConvert_validHumanCreatedHtmlContent_returnsCorrectKeywords() throws IOException {
        final String httpResponse = TestHelper.readFileAsString(
            new File("src/test/resources/html/the-month-in-wordpress-june-2017.html")
        );

        when(httpService.getContent(anyString()))
            .thenReturn(httpResponse);
        
        final Article a = service.convert("human article");
        final List<String> keywords = a.keywords(6);

        assertEquals(6, keywords.size());
        assertEquals("wordpress", keywords.get(0));
        assertEquals("wordcamp", keywords.get(1));
        assertEquals("you", keywords.get(2));
        assertEquals("community", keywords.get(3));
        assertEquals("europe", keywords.get(4));
        assertEquals("all", keywords.get(5));
    }
}
