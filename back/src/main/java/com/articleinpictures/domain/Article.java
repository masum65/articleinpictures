package com.articleinpictures.domain;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author Teemu Hirvonen
 */
public final class Article {
    private final Map<String, Integer> keywords = new HashMap<>();
    private final String rawText;

    /**
     * Creates a new {@code Article} with the provided raw text.
     * @param rawText 
     */
    public Article(final String rawText) {
        this.rawText = rawText;
    }

    /**
     * Adds a keyword to the article. If the keyword is already present, increments its count.
     * 
     * @param keyword The keyword to add.
     */
    public void add(final String keyword) {
        if (null == keyword || keyword.isEmpty()) return;
        
        if (!keywords.containsKey(keyword)) {
            keywords.put(keyword, 1);
        } else {
            keywords.put(keyword, keywords.get(keyword) + 1);
        }
    }
    
    /**
     * Returns a list of keywords based on the given comparator and a limit.
     * 
     * @param cmp The comparator to use to sort keywords.
     * @param limit The limit on number of keywords to return.
     * @return The list of keywords in this Article.
     */
    public List<String> keywords(final Comparator<? super Entry<String, Integer>> cmp, final long limit) {
        return keywords.entrySet().stream()
            .sorted((a, b) -> b.getValue() - a.getValue())
            .map(e -> e.getKey())
            .limit(limit)
            .collect(Collectors.toList());
    }
    
    /**
     * Returns a list of keywords in descending order based on occurrence.
     * 
     * @param limit The limit on number of keywords to return.
     * @return The list of keywords.
     */
    public List<String> keywords(final long limit) {
        return keywords((a, b) -> b.getValue() - a.getValue(), limit);
    }

    public String getRawText() {
        return rawText;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.rawText);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Article other = (Article) obj;
        if (!Objects.equals(this.rawText, other.rawText)) {
            return false;
        }
        return true;
    }
}
