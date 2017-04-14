package com.flickr_gallery.tigerspike.tigerspikegallery.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.util.Log;
import android.widget.ImageView;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static android.graphics.BlurMaskFilter.Blur.NORMAL;

/**
 * Created by Netaq on 4/14/2017.
 */

public class Common {

    private static ProgressView progress;

    public static void setThumbnailImage(final Context context, String url, ImageView imageView){

        Picasso.with(context).load(url).placeholder(R.drawable.image_loading_placeholder).fit().into(imageView);

    }

    public static ProgressView getProgress(){
        progress = new ProgressView();

        return progress;
    }

    public static List<FlickrFeedResponse.Item> sortByDateTaken(List<FlickrFeedResponse.Item> list){

        Collections.sort(list, new FlickrFeedResponse.Item.DateTakenComparator());

        return list;
    }

    public static List<FlickrFeedResponse.Item> sortByDatePublished(List<FlickrFeedResponse.Item> list){

        Collections.sort(list, new FlickrFeedResponse.Item.DatePublishedComparator());

        return list;
    }

}
