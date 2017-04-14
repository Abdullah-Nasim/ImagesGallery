package com.flickr_gallery.tigerspike.tigerspikegallery.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.flickr_gallery.tigerspike.tigerspikegallery.utils.Common;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 4/14/2017.
 */

public class ImagePreviewActivity extends AppCompatActivity {

    @BindView(R.id.image_preview)
    ImageView preview;

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

        //Getting the feed item stored in intent extra.
        imageItem = (FlickrFeedResponse.Item) getIntent().getSerializableExtra("ImageInfo");

        //Setting up the preview image.
        Common.setPreviewImage(ImagePreviewActivity.this, imageItem.getMedia().getM(), preview);

    }



    private void setToolbar() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            this.finish();
        }else if(item.getItemId() == R.id.action_save_to_gallery){

            //Save the image to gallery.
            Common.imageDownload(ImagePreviewActivity.this, imageItem.getMedia().getM(), imageItem.getTitle());
        }

        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.preview_screen_menu, menu);
        return true;

    }

}
