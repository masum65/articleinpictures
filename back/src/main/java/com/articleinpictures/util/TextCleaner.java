package com.articleinpictures.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A helper class for cleaning a text article.
 * 
 * @author Teemu Hirvonen
 */
public class TextCleaner {
    /** A map of stop words. **/
    private static final Map<String, Boolean> stopwords = new HashMap<>();

    /** Initialize the list of stop words. **/
    static {
        Arrays.asList(
            "i", "a", "about", "and", "an", "are", "as", "at", "be", "by", "com", "for", "from", "how", "in", "is", "it", "of",
            "on", "or", "that", "the", "this", "to", "was", "what", "when", "where", "who", "will", "with", "the"
        ).forEach(w -> stopwords.put(w, Boolean.TRUE));
    }
    
    /**
     * Cleans the given text by removing unnecessary whitespace, punctuation and stop words.
     * 
     * @param text The text to clean.
     * @return The cleaned text.
     */
    public static String clean(final String text) {
        final List<String> cleaned = Arrays.asList(
            text.trim()
                .toLowerCase()
                .replaceAll("\\s{2,}", " ")
                .replaceAll("[^\\sa-z0-9']", "")
                .split(" ")
        );
        
        return String.join(
            " ",
            cleaned.stream()
                .filter(s -> !stopwords.containsKey(s))
                .collect(Collectors.toList())
        );
    }
}
