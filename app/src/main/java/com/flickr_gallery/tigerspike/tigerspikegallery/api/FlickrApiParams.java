package com.flickr_gallery.tigerspike.tigerspikegallery.api;

/**
 * Created by Abdullah on 4/13/2017.
 * Email: abdullahdxbid@gmail.com
 */

public class FlickrApiParams {

    String lang;
    String format;
    int nojsoncallback;
    String tags;

    /**
     * This constructor takes all the required params for a flickr API call.
     * @param lang
     * @param format
     * @param nojsoncallback
     * @param tags
     */
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
