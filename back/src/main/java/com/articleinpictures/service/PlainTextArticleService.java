package com.articleinpictures.service;

import com.articleinpictures.domain.Article;
import com.articleinpictures.util.TextCleaner;
import org.springframework.stereotype.Service;

/**
 * An {@link ArticleService} that turns a plain text article into an {@link Article} object.
 * 
 * @author Teemu Hirvonen
 */
@Service("plaintext")
public class PlainTextArticleService implements ArticleService<String> {
    /**
     * Concatenates a sublist of the provided keyword list into a String of keyword(s).
     * 
     * @param kws The List of keywords.
     * @param startIndex The index to start from.
     * @param amount The amount of words to gobble.
     * @return The keyword string in which words are join by spaces.
     */
    private static String concat(final String[] kws, final int startIndex, final int amount) {
        final String[] temp = new String[amount];

        int j = 0;
        for (int i = startIndex; i < (startIndex + amount); i++) {
            temp[j] = kws[i];
            j++;
        }

        return String.join(" ", temp);
    }
    
    @Override
    public Article convert(final String text) {
        if (null == text) throw new NullPointerException();
        
        final Article article = new Article(text);
        
        final String cleaned = TextCleaner.clean(text);
        final String[] words = cleaned.split(" ");
        final int maxLevel = 3;

        final int len = words.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < maxLevel && (i + j) < len; j++) {
                article.add(concat(words, i, j + 1));
            }
        }
        
        return article;
    }
}
