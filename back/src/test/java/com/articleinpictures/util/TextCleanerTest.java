package com.articleinpictures.util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Teemu Hirvonen
 */
public class TextCleanerTest {
    @Test
    public void testClean_hasUnnecessaryWhitespace_whitespaceAreRemoved() {
        final String source = "   Hello  world! ";
        
        assertEquals("hello world", TextCleaner.clean(source));
    }
    
    @Test
    public void testClean_hasPunctuation_punctuationIsRemoved() {
        final String source = "Aa, bb, cc.";
        
        assertEquals("aa bb cc", TextCleaner.clean(source));
    }
    
    @Test
    public void testClean_hasNumbers_numbersAreNotRemoved() {
        final String source = "Mary had 1, 2, 3 and 4 little lambs";
        
        assertEquals("mary had 1 2 3 4 little lambs", TextCleaner.clean(source));
    }
    
    @Test
    public void testClean_hasStopWords_stopWordsAreRemoved() {
        final String source = "Mary had a lamb";
        
        assertEquals("mary had lamb", TextCleaner.clean(source));
    }
}
