package com.flickr_gallery.tigerspike.tigerspikegallery.screens;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.flickr_gallery.tigerspike.tigerspikegallery.utils.Common;
import com.flickr_gallery.tigerspike.tigerspikegallery.utils.ProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 4/14/2017.
 */

public class ImagePreviewActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 101;

    @BindView(R.id.image_preview)
    ImageView preview;

    private ProgressView progressView;


    FlickrFeedResponse.Item imageItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_preview_layout);
        ButterKnife.bind(this);

        //The following method call is responsible of setting up the back navigation in toolbar.
        setToolbar();

        // The following method call is responsible of initializing the preview and getting the required intent extra.
        initViews();
    }

    private void initViews() {

        //This following lines sets up the loading view.
        progressView = Common.getProgress();
        progressView.setCancelable(false);

        //Getting the feed item stored in intent extra.
        imageItem = (FlickrFeedResponse.Item) getIntent().getSerializableExtra("ImageInfo");

        //Setting up the preview image.
        Common.setPreviewImage(ImagePreviewActivity.this, imageItem.getMedia().getM(), preview, progressView);

    }



    private void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){

            this.finish();

        }else if(item.getItemId() == R.id.action_save_to_gallery){

            //Check that if external storage permission is granted or not.
            if(checkStoragePermissionGranted()){

                //Save the image to gallery.
                Common.imageDownload(ImagePreviewActivity.this, imageItem.getMedia().getM(), imageItem.getPublished());
            }

        }else if(item.getItemId() == R.id.action_open_in_browser){

            //Open Image in Browser.
            Common.openImageInBrowser(ImagePreviewActivity.this, imageItem.getMedia().getM());
        }else if(item.getItemId() == R.id.action_email_image){

            Common.imageEmail(ImagePreviewActivity.this, imageItem.getMedia().getM(), imageItem.getPublished());
        }

        return super.onOptionsItemSelected(item);

    }


    private boolean checkStoragePermissionGranted() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            return false;

        }else{
            return true;
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.preview_screen_menu, menu);
        return true;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Common.imageDownload(ImagePreviewActivity.this, imageItem.getMedia().getM(), imageItem.getPublished());
                } else {
                    finish();
                }
                return;
            }

        }
    }


}
