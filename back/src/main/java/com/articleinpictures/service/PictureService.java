package com.articleinpictures.service;

import com.articleinpictures.domain.Article;
import com.articleinpictures.domain.Picture;
import java.util.List;

/**
 * @author Teemu Hirvonen
 */
public interface PictureService {
    /**
     * Returns a {@code CompletableFuture} that returns a list of pictures for the provided {@code Article}.
     * @param article The article to get pictures for.
     * @return The List of {@code Picture}s.
     */
    public List<Picture> listPicturesForArticle(Article article);
}
