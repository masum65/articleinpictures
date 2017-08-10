package com.articleinpictures.service;

import com.articleinpictures.domain.Article;
import com.articleinpictures.domain.Picture;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * An {@code PictureService} that fetches pictures from the {@literal Pixabay} API.
 * 
 * @author Teemu Hirvonen
 */
@Service("pixabay")
public class PixabayPictureService implements PictureService {
    public static final String API_URL_TEMPLATE = "https://pixabay.com/api/?key=%s&q=%s&image_type=photo&pretty=false";

    @Autowired
    private RestTemplate client;
    
    @Value("${pictureservices.pixabay.api-key}")
    private String apiKey;
    
    /**
     * Fetches a list of {@code Picture}s for the provided keyword.
     * 
     * @param keyword The keyword to search with.
     * @return The List of {@code Picture}s.
     */
    public List<Picture> fetchPicturesForKeyword(final String keyword) {
        if (null == keyword || keyword.isEmpty()) return new ArrayList<>();
        
        try {
            final Map<String, Object> values = client.getForObject(
                String.format(API_URL_TEMPLATE, apiKey, URLEncoder.encode(keyword, "UTF-8")),
                Map.class
            );

            if (values == null || !values.containsKey("total") || ((int) values.get("total")) == 0) {
                return new ArrayList<>();
            }

            final List<Map<String, Object>> hits = (List<Map<String, Object>>) values.get("hits");

            return hits.stream()
                .map(m -> {
                    return Picture.builder(String.valueOf(m.getOrDefault("id", UUID.randomUUID().toString())))
                        .width((int) m.getOrDefault("imageWidth", 0))
                        .height((int) m.getOrDefault("imageHeight", 0))
                        .url((String) m.getOrDefault("pageURL", ""))
                        .thumbnailWidth((int) m.getOrDefault("webformatWidth", 0))
                        .thumbnailHeight((int) m.getOrDefault("webformatHeight", 0))
                        .thumbnailUrl((String) m.getOrDefault("webformatURL", ""))
                        .source("pixabay")
                        .build();
                }).collect(Collectors.toList());
        } catch (final UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    @Override
    public List<Picture> listPicturesForArticle(final Article article) {
        return article.keywords(5).stream()
            .map(this::fetchPicturesForKeyword)
            .flatMap(l -> l.stream())
            .collect(Collectors.toList());
    }
}
