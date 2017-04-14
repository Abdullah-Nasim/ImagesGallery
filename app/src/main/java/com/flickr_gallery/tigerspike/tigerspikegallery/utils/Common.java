package com.flickr_gallery.tigerspike.tigerspikegallery.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by Netaq on 4/14/2017.
 */

public class Common {

    private static ProgressView progress;

    //Set the thumbnail image.
    public static void setThumbnailImage(final Context context, String url, ImageView imageView){

        Picasso.with(context).load(url).placeholder(R.drawable.placeholder_thumbnail).fit().into(imageView);

    }

    //Set the image in preview layout.
    public static void setPreviewImage(final Context context, String url, ImageView imageView){

        Picasso.with(context).load(url).placeholder(R.drawable.image_preview_placeholder).fit().into(imageView);

    }

    //Returns the loading view where needed.
    public static ProgressView getProgress(){

        progress = new ProgressView();

        return progress;
    }

    //Sort the pictures based on Date Taken
    public static List<FlickrFeedResponse.Item> sortByDateTaken(List<FlickrFeedResponse.Item> list){

        Collections.sort(list, new FlickrFeedResponse.Item.DateTakenComparator());

        return list;
    }

    //Sort the pictures based on Published Date
    public static List<FlickrFeedResponse.Item> sortByDatePublished(List<FlickrFeedResponse.Item> list){

        Collections.sort(list, new FlickrFeedResponse.Item.DatePublishedComparator());

        return list;
    }

    //The following methods implementation saves the image to external storage.
    public static void imageDownload(Context context, String url, String title){
        Picasso.with(context)
                .load(url)
                .into(getTarget(context, url, title));
    }

    //target to save
    private static Target getTarget(final Context context, final String url, final String title){
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        String path = Environment.getExternalStorageDirectory() + "/" + "TigerSpike Gallery/";
                        File dir = new File(path);

                        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/TigerSpike Gallery/" + title + ".jpg");

                        if(!dir.exists()){
                            dir.mkdir();
                        }
                        try {
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                            ostream.flush();
                            ostream.close();
                            galleryAddPic(file, context);
                        } catch (IOException e) {
                            Log.e("IOException", e.getLocalizedMessage());
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }

    //rescan the gallery once the image is added
    private static void galleryAddPic(File file, Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

}
