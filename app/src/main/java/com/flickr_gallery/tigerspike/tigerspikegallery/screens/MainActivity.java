package com.flickr_gallery.tigerspike.tigerspikegallery.screens;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.api.FlickrApiParams;
import com.flickr_gallery.tigerspike.tigerspikegallery.bals.FlickrFeedBAL;
import com.flickr_gallery.tigerspike.tigerspikegallery.bals.interfaces.FeedFetchListener;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.flickr_gallery.tigerspike.tigerspikegallery.screens.adapter.GalleryRecyclerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.gallery_recycler)
    RecyclerView recycler;

    GalleryRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ButterKnife.setDebug(true);

        //The following function call is responsible for setting up the toolbar.
        setToolbar();

        //The following function call is responsible for initializing the components;
        initViews();

    }

        private void initViews() {

            //The following function call is responsible to fetch the Flickr Feeds.
            fetchFlickrFeed();

        }

        private void fetchFlickrFeed() {

        FlickrFeedBAL.getFlickrFeed(new FlickrApiParams("en", "json", 1), new FeedFetchListener() {
            @Override
            public void onFeedFetched(FlickrFeedResponse response) {

                adapter = new GalleryRecyclerAdapter(MainActivity.this, response);
                recycler.setLayoutManager(new GridLayoutManager(MainActivity.this,3, LinearLayoutManager.VERTICAL, false));
                recycler.setAdapter(adapter);

            }

            @Override
            public void onFeedNotFetched() {

            }

            @Override
            public void onNetworkFailure() {

            }

            @Override
            public void onResponseIssue() {

            }

            @Override
            public void onRequestTimeout() {

            }

            @Override
            public void onException(String errorMessage) {

            }
        });
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
