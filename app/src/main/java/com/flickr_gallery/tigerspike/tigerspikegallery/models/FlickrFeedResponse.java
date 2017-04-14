package com.flickr_gallery.tigerspike.tigerspikegallery.models;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Netaq on 4/13/2017.
 */
public class FlickrFeedResponse implements Serializable{

    public String title;
    public String link;
    public String description;
    public String modified;
    public String generator;
    public List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items){
        this.items = items;
    }

    public class Media implements Serializable{

        public String m;

        public String getM() {
            return m;
        }

        public void setM(String m) {
            this.m = m;
        }
    }

    public static class Item implements Serializable {

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

        public static class DateTakenComparator implements Comparator<Item>
        {

            @Override
            public int compare(Item item, Item t1) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

                try {
                    if (formatter.parse(item.getDate_taken().substring(0, item.getDate_taken().lastIndexOf('-'))).getTime() > formatter.parse(t1.getDate_taken().substring(0, item.getDate_taken().lastIndexOf('-'))).getTime()) {
                        return 1;
                    }
                    else if (formatter.parse(item.getDate_taken().substring(0, item.getDate_taken().lastIndexOf('-'))).getTime() < formatter.parse(t1.getDate_taken().substring(0, item.getDate_taken().lastIndexOf('-'))).getTime()) {
                        return -1;
                    }
                    else {
                        return 0;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        }

        public static class DatePublishedComparator implements Comparator<Item>
        {
            @Override
            public int compare(Item item, Item t1) {
                DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

                try {
                    if (formatter.parse(item.getPublished()).getTime() > formatter.parse(t1.getPublished()).getTime()) {
                        return 1;
                    }
                    else if (formatter.parse(item.getPublished()).getTime() < formatter.parse(t1.getPublished()).getTime()) {
                        return -1;
                    }
                    else {
                        return 0;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                    return 0;
                }
            }
        }


    }
}



