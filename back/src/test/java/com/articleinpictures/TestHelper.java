package com.articleinpictures;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

/**
 * @author Teemu Hirvonen
 */
public class TestHelper {
    /**
     * Attempts to return the provided file as a a String.
     * 
     * @param file The file to parse.
     * @return The file as a String.
     * @throws IOException 
     */
    public static String readFileAsString(final File file) throws IOException {
        return  Files.lines(Paths.get(file.toURI())).collect(Collectors.joining());
    }
}
