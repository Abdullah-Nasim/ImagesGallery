package com.flickr_gallery.tigerspike.tigerspikegallery.models;

import java.util.List;

/**
 * Created by Netaq on 4/13/2017.
 */
public class FlickrFeedResponse {

    public String title;
    public String link;
    public String description;
    public String modified;
    public String generator;
    public List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public class Media {

        public String m;

        public String getM() {
            return m;
        }

        public void setM(String m) {
            this.m = m;
        }
    }

    public class Item {

        public String title;
        public String link;
        public Media media;
        public String date_taken;
        public String description;
        public String published;
        public String author;
        public String author_id;
        public String tags;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Media getMedia() {
            return media;
        }

        public void setMedia(Media media) {
            this.media = media;
        }

        public String getDate_taken() {
            return date_taken;
        }

        public void setDate_taken(String date_taken) {
            this.date_taken = date_taken;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthor_id() {
            return author_id;
        }

        public void setAuthor_id(String author_id) {
            this.author_id = author_id;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }
    }
}



