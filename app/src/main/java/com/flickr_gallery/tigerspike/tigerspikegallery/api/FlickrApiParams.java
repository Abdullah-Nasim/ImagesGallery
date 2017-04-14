package com.flickr_gallery.tigerspike.tigerspikegallery.api;

/**
 * Created by Netaq on 4/13/2017.
 */

public class FlickrApiParams {

    String lang;
    String format;
    int nojsoncallback;
    String tags;

    public FlickrApiParams(String lang, String format, int nojsoncallback, String tags) {
        this.lang = lang;
        this.format = format;
        this.nojsoncallback = nojsoncallback;
        this.tags = tags;
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getNojsoncallback() {
        return nojsoncallback;
    }

    public void setNojsoncallback(int nojsoncallback) {
        this.nojsoncallback = nojsoncallback;
    }
}
