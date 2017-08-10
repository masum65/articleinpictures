package com.articleinpictures.controller;

import com.articleinpictures.Application;
import com.articleinpictures.TestHelper;
import com.articleinpictures.domain.PictureResponse;
import com.articleinpictures.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.eq;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import com.articleinpictures.service.PictureService;

/**
 * Integration tests for the /api/pictures/* endpoints.
 * 
 * @author Teemu Hirvonen
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PicturesControllerTest {
    @Mock
    private RestTemplate restTemplate;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("plaintext")
    private ArticleService<String> plainTextArticleService;

    @Autowired
    @InjectMocks
    @Qualifier("pixabay")
    private PictureService pictureService;
    
    @Test
    public void testText_emptyBody_returns400BadRequest() {
        final ResponseEntity<PictureResponse> entity = testRestTemplate.postForEntity("/api/pictures/text",
            "",
            PictureResponse.class
        );
        
        assertEquals(400, entity.getStatusCodeValue());
    }

    @Test
    public void testText_validArticleSingleKeyword_returnsValidResponse() throws IOException {
        final String articleBody = "yellow";
        final String apiUrl = "https://pixabay.com/api/?key=test&q=yellow&image_type=photo&pretty=false";
        
        final Map<String, Object> validResponse = objectMapper.readValue(
            TestHelper.readFileAsString(new File("src/test/resources/json/yellow.json")),
            Map.class
        );

        when(restTemplate.getForObject(eq(apiUrl), eq(Map.class)))
            .thenReturn(validResponse);
        
        final ResponseEntity<PictureResponse> entity = testRestTemplate.postForEntity("/api/pictures/text",
            articleBody,
            PictureResponse.class
        );
        
        final PictureResponse response = entity.getBody();
        
        assertEquals(20, response.getHits());
        
        verify(restTemplate, times(1)).getForObject(eq(apiUrl), eq(Map.class));
    }

    @Test
    public void testText_validArticleMultipleKeywords_returnsValidResponse() throws IOException {
        final String articleBody = "yellow flowers";
        final String firstApiUrl = "https://pixabay.com/api/?key=test&q=yellow&image_type=photo&pretty=false";
        final String secondApiUrl = "https://pixabay.com/api/?key=test&q=yellow+flowers&image_type=photo&pretty=false";
        final String thirdApiurl = "https://pixabay.com/api/?key=test&q=flowers&image_type=photo&pretty=false";
        
        final Map<String, Object> firstResponse = objectMapper.readValue(
            TestHelper.readFileAsString(new File("src/test/resources/json/yellow.json")),
            Map.class
        );
        final Map<String, Object> secondResponse = objectMapper.readValue(
            TestHelper.readFileAsString(new File("src/test/resources/json/yellow-flowers.json")),
            Map.class
        );
        final Map<String, Object> thirdResponse = objectMapper.readValue(
            TestHelper.readFileAsString(new File("src/test/resources/json/flowers.json")),
            Map.class
        );

        when(restTemplate.getForObject(eq(firstApiUrl), eq(Map.class)))
            .thenReturn(firstResponse);
        when(restTemplate.getForObject(eq(secondApiUrl), eq(Map.class)))
            .thenReturn(secondResponse);
        when(restTemplate.getForObject(eq(thirdApiurl), eq(Map.class)))
            .thenReturn(thirdResponse);

        final ResponseEntity<PictureResponse> entity = testRestTemplate.postForEntity("/api/pictures/text",
            articleBody,
            PictureResponse.class
        );

        final PictureResponse response = entity.getBody();

        assertEquals(60, response.getHits());

        verify(restTemplate, times(1)).getForObject(eq(firstApiUrl), eq(Map.class));
        verify(restTemplate, times(1)).getForObject(eq(secondApiUrl), eq(Map.class));
        verify(restTemplate, times(1)).getForObject(eq(thirdApiurl), eq(Map.class));
    }
    
    @Test
    public void testUrl_emptyUrl_returns400BadRequest() {
        final ResponseEntity<PictureResponse> entity = testRestTemplate.postForEntity("/api/pictures/url",
            "",
            PictureResponse.class
        );

        assertEquals(400, entity.getStatusCodeValue());
    }
    
    @Test
    public void testUrl_invalidUrl_returns400BadRequest() {
        final ResponseEntity<PictureResponse> entity = testRestTemplate.postForEntity("/api/pictures/url",
            "not-an-url",
            PictureResponse.class
        );

        assertEquals(400, entity.getStatusCodeValue());
    }
}
