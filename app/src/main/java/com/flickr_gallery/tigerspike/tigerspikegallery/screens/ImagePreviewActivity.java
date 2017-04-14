package com.flickr_gallery.tigerspike.tigerspikegallery.screens;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;

/**
 * Created by Netaq on 4/14/2017.
 */

public class ImagePreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_preview_layout);
    }
}
