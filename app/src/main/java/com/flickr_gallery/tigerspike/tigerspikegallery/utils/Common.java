package com.flickr_gallery.tigerspike.tigerspikegallery.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.squareup.picasso.Callback;
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

    private static boolean checkEmail;

    /**
     * This function takes the following params and uses picasso library to render the image in  given image view.
     * This function is being used by recycler adapter to set the thumbnail images.
     * @param context
     * @param url
     * @param imageView
     */
    public static void setThumbnailImage(final Context context, String url, ImageView imageView){

        Picasso.with(context).load(url).placeholder(R.drawable.placeholder_thumbnail).fit().into(imageView);

    }

    /**
     * This function takes the following params and uses picasso library to render the image in given image view.
     * This function is being used by the ImagePreviewActivity class to render the image in preview image vive.
     * @param context
     * @param url
     * @param imageView
     * @param progressView
     */
    public static void setPreviewImage(final AppCompatActivity context, String url, ImageView imageView, final ProgressView progressView){

        //Show loading view.
        progressView.show(context.getSupportFragmentManager(), "");

        Picasso.with(context).load(url).resize(1600,1200).centerCrop().into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressView.dismiss();
            }

            @Override
            public void onError() {

            }
        });

    }

    //Returns the loading view where needed.
    public static ProgressView getProgress(){

        progress = new ProgressView();

        return progress;
    }


    /**
     * This function takes the coordinate layout and error message as a parameter from any activity or fragment
     * ans shows the Snack bar on respective coordinate layout.
     * @param coordinatorLayout
     * @param string
     */
    public static void showSnackBarLong(CoordinatorLayout coordinatorLayout, String string) {

        Snackbar.make(coordinatorLayout, string,Snackbar.LENGTH_LONG).show();

    }

    /**
     * This function is responsible for opening the image in browser.
     * @param context
     * @param url
     */
    public static void openImageInBrowser(Context context, String url){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(browserIntent);
    }


    /**
     *
     * @param context
     * @param file
     */
    public static void sendImageViaEmail(Context context, File file){

        Uri uri = Uri.fromFile(file);

        //Email the saved image.
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/jpeg");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Shared image from TigerSpike Gallery");
        intent.putExtra(Intent.EXTRA_TEXT, "This image is being sent from TigerSpike Test Assignment!");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, "Email image"));

    }


    /**
     * This function takes flickr feed list as a parameter and sorts the list based on Date Taken
     * @param list
     * @return
     */
    public static List<FlickrFeedResponse.Item> sortByDateTaken(List<FlickrFeedResponse.Item> list){

        Collections.sort(list, new FlickrFeedResponse.Item.DateTakenComparator());

        return list;
    }


    /**
     * This function takes flickr feed list as a parameter and sorts the list based on Date Published
     * @param list
     * @return
     */
    public static List<FlickrFeedResponse.Item> sortByDatePublished(List<FlickrFeedResponse.Item> list){

        Collections.sort(list, new FlickrFeedResponse.Item.DatePublishedComparator());

        return list;
    }


    /**
     * This function downloads the image to save in gallery
     * @param context
     * @param url
     * @param title
     */
    public static void imageDownload(Context context, String url, String title){
        Picasso.with(context)
                .load(url)
                .into(getTarget(context, url, title));
    }

    /**
     * This function is responsible for downloading the image and sending it via email or any other application.
     * The checkEmail specifies that the Target method is being called for saving the image to share.
     * @param context
     * @param url
     * @param title
     */
    public static void imageEmail(Context context, String url, String title){
        checkEmail = true;
        Picasso.with(context)
                .load(url)
                .into(getTarget(context, url, title));
    }


    /**
     * This function is being called by imageDownload to save the image in external storage.
     * @param context
     * @param url
     * @param title
     * @return
     */
    private static Target getTarget(final Context context, final String url, final String title){
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {

                    @Override
                    public void run() {

                        String path = "TigerSpike Gallery/";
                        File dir = new File(Environment.getExternalStorageDirectory(),path);

                        if(!dir.exists()){
                            dir.mkdir();
                        }

                        File file = new File(dir,title+".jpg");

                        try {
                            file.createNewFile();
                            FileOutputStream oStream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, oStream);
                            oStream.flush();
                            oStream.close();
                            galleryAddPic(file, context);

                            if(checkEmail){
                                sendImageViaEmail(context,file);
                            }

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


    /**
     * This function enables the gallery to find the downloaded picture.
     * @param file
     * @param context
     */
    private static void galleryAddPic(File file, Context context) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(file);
        mediaScanIntent.setData(contentUri);
        context.sendBroadcast(mediaScanIntent);
    }

}
