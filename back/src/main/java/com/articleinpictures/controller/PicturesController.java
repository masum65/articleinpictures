package com.articleinpictures.controller;

import com.articleinpictures.domain.PictureResponse;
import com.articleinpictures.exception.InvalidURLException;
import com.articleinpictures.service.ArticleService;
import java.util.concurrent.CompletableFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.articleinpictures.service.PictureService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * The {@literal /api/pictures} endpoints.
 * 
 * @author Teemu Hirvonen
 */
@RestController
@RequestMapping("/api/pictures")
public class PicturesController {
    @Autowired
    @Qualifier("pixabay")
    private PictureService pictureService;
    
    @Autowired
    @Qualifier("plaintext")
    private ArticleService<String> plainTextArticleService;
    
    @Autowired
    @Qualifier("html")
    private ArticleService<String> htmlArticleService;
    
    /**
     * Returns a list of {@code Picture}s using a pure text input.
     * 
     * @param body The article body.
     * @return The List of {@code Picture}s.
     */
    @ApiOperation(value = "Analyzes the provided text and returns a list of related pictures.",
        response = PictureResponse.class)
    @RequestMapping(value = "/text", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
    public CompletableFuture<PictureResponse> text(@RequestBody final String body) {
        return CompletableFuture.supplyAsync(() -> plainTextArticleService.convert(body))
            .thenApply(a -> pictureService.listPicturesForArticle(a))
            .thenApply(pictures -> {
                return PictureResponse.builder()
                    .hits(pictures.size())
                    .pictures(pictures)
                    .build();
            });
    }
    
    /**
     * Returns a list of {@code Picture}s using an URL to an article.
     * 
     * @param url The URL to the article.
     * @return The List of {@code Picture}s.
     */
    @ApiOperation(value = "Analyzes the content of the provided URL and returns a list of related pictures.",
        response = PictureResponse.class)
    @RequestMapping(value = "/url", method = RequestMethod.POST, consumes = "text/plain", produces = "application/json")
    public CompletableFuture<PictureResponse> url(@RequestBody final String url) {
        return CompletableFuture.supplyAsync(() -> htmlArticleService.convert(url))
            .thenApply(a -> pictureService.listPicturesForArticle(a))
            .thenApply(pictures -> {
                return PictureResponse.builder()
                    .hits(pictures.size())
                    .pictures(pictures)
                    .build();
            });
    }
    
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidURLException.class)
    public String handleException(final InvalidURLException ex) {
        return String.format("{ \"message\": \"%s\" }", ex.getMessage());
    }
}
