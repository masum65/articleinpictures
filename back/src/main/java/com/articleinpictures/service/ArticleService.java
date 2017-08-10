package com.articleinpictures.service;

import com.articleinpictures.domain.Article;

/**
 * @author Teemu Hirvonen
 * @param <T>
 */
public interface ArticleService<T> {
    /**
     * Converts the provided object into an {@code Article}.
     * 
     * @param obj The object to use in conversion.
     * @return The new {@code Article}.
     */
    Article convert(T obj);
}
