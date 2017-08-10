package com.articleinpictures.service;

import com.articleinpictures.Application;
import com.articleinpictures.domain.Article;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * @author Teemu Hirvonen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PixabayImageServiceTest {
    @Mock
    private RestTemplate restTemplate;
    
    @Autowired
    @InjectMocks
    @Qualifier("pixabay")
    private PictureService service;

    @Test
    public void testListImagesForArticle_nullResponse_returnsEmptyImagesList() {
        when(restTemplate.getForObject(anyString(), any()))
            .thenReturn(null);

        assertTrue(
            "Images list should be empty for null response",
            service.listPicturesForArticle(new Article("")).isEmpty()
        );
    }
    
    @Test
    public void testListImagesForArticle_emptyResponse_returnsEmptyImagesList() {
        when(restTemplate.getForObject(anyString(), any()))
            .thenReturn(new HashMap<>());
        
        assertTrue(
            "Images list should be empty for empty response",
            service.listPicturesForArticle(new Article("")).isEmpty()
        );
    }
    
    @Test
    public void testListImagesForArticle_zeroHitsResponse_retrusnEmptyImagesList() {
        final Map<String, Object> response = new HashMap<>();
        
        response.put("hits", 0);
        
        when(restTemplate.getForObject(anyString(), any()))
            .thenReturn(response);

        assertTrue(
            "Images list should be empty for zero hits response",
            service.listPicturesForArticle(new Article("")).isEmpty()
        );
    }
    
    @Test
    public void testListImagesForArticle_nonEmptyResponse_returnsAListOfImages() {
        final Map<String, Object> response = new HashMap<>();
        final List<Map<String, Object>> hits = new ArrayList<>();
        final Map<String, Object> firstHit = new HashMap<>();
        final Map<String, Object> secondHit = new HashMap<>();
        final int expectedSize = 2;
        
        firstHit.put("width", 1);
        firstHit.put("height", 2);
        secondHit.put("width", 3);
        secondHit.put("height", 4);
        
        hits.add(firstHit);
        hits.add(secondHit);
        
        response.put("total", expectedSize);
        response.put("hits", hits);
        
        when(restTemplate.getForObject(anyString(), any()))
            .thenReturn(response);
        
        final Article article = new Article("");
        article.add("first");
        
        assertEquals(
            String.format("Image list should contain %d images", expectedSize),
            expectedSize,
            service.listPicturesForArticle(article).size()
        );
    }
}
