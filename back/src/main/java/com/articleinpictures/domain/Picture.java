package com.articleinpictures.domain;

import java.util.Objects;

/**
 * @author Teemu Hirvonen
 */
public class Picture {
    public static class Builder {
        private String id;
        private int width;
        private int height;
        private String url;
        private int thumbnailWidth;
        private int thumbnailHeight;
        private String thumbnailUrl;
        private String source;

        public Builder(final String id) {
            this.id = id;
        }
        
        public Builder width(final int width) {
            this.width = width;
            return this;
        }
        
        public Builder height(final int height) {
            this.height = height;
            return this;
        }
        
        public Builder url(final String url) {
            this.url = url;
            return this;
        }
        
        public Builder thumbnailWidth(final int thumbnailWidth) {
            this.thumbnailWidth = thumbnailWidth;
            return this;
        }
        
        public Builder thumbnailHeight(final int thumbnailHeight) {
            this.thumbnailHeight = thumbnailHeight;
            return this;
        }
        
        public Builder thumbnailUrl(final String thumbnailUrl) {
            this.thumbnailUrl = thumbnailUrl;
            return this;
        }
        
        public Builder source(final String source) {
            this.source = source;
            return this;
        }
        
        public Picture build() {
            return new Picture(this);
        }
    }
    
    private String id;
    private int width;
    private int height;
    private String url;
    private int thumbnailWidth;
    private int thumbnailHeight;
    private String thumbnailUrl;
    private String source;
    
    public Picture() {}
    
    private Picture(final Builder b) {
        this.id = b.id;
        this.width = b.width;
        this.height = b.height;
        this.url = b.url;
        this.thumbnailWidth = b.thumbnailWidth;
        this.thumbnailHeight = b.thumbnailHeight;
        this.thumbnailUrl = b.thumbnailUrl;
        this.source = b.source;
    }
    
    public static Builder builder(final String id) {
        return new Builder(id);
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getThumbnailWidth() {
        return thumbnailWidth;
    }

    public void setThumbnailWidth(int thumbnailWidth) {
        this.thumbnailWidth = thumbnailWidth;
    }

    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    public void setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Picture other = (Picture) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
}
