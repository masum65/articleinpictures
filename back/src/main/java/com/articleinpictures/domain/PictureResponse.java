package com.articleinpictures.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a a valid response in the controller containing a list of {@code Picture}s.
 * 
 * @author Teemu Hirvonen
 */
public class PictureResponse {
    public static class Builder {
        private int hits;
        private List<Picture> pictures;
        
        public Builder hits(final int hits) {
            this.hits = hits;
            return this;
        }
        
        public Builder pictures(final List<Picture> pictures) {
            this.pictures = pictures;
            return this;
        }
        
        public PictureResponse build() {
            return new PictureResponse(this);
        }
    }
    
    private int hits;
    private List<Picture> pictures;

    private PictureResponse(final Builder b) {
        this.hits = b.hits;
        this.pictures = b.pictures != null ? b.pictures : new ArrayList<>();
    }
    
    public PictureResponse() {}
    
    public static Builder builder() {
        return new Builder();
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }
}
