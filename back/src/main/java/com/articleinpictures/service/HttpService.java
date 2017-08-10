package com.articleinpictures.service;

import java.io.IOException;

/**
 * @author Teemu Hirvonen
 */
public interface HttpService {
    String getContent(String url) throws IOException;
}
