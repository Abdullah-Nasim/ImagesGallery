package com.flickr_gallery.tigerspike.tigerspikegallery.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Netaq on 4/14/2017.
 */

public class Common {

    public static void setThumbnailImage(Context context, String url, ImageView imageView){
        Picasso.with(context).setLoggingEnabled(true);
        Picasso.with(context).load(url).fit().into(imageView);
    }
}
