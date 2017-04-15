package com.flickr_gallery.tigerspike.tigerspikegallery.screens;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.api.FlickrApiParams;
import com.flickr_gallery.tigerspike.tigerspikegallery.bals.FlickrFeedBAL;
import com.flickr_gallery.tigerspike.tigerspikegallery.bals.interfaces.FeedFetchListener;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.flickr_gallery.tigerspike.tigerspikegallery.screens.adapter.GalleryRecyclerAdapter;
import com.flickr_gallery.tigerspike.tigerspikegallery.utils.Common;
import com.flickr_gallery.tigerspike.tigerspikegallery.utils.ProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity{

    @BindView(R.id.gallery_recycler)
    RecyclerView recycler;

    @BindView(R.id.main_appbar_logo)
    ImageView appBarLogo;

    @BindView(R.id.search_input)
    TextInputEditText searchInput;

    @BindView(R.id.main_activity_coordinate_layout)
    CoordinatorLayout coordinatorLayout;

    private ProgressView progressView;

    private GalleryRecyclerAdapter adapter;

    private GridLayoutManager layoutManager;

    private MenuItem searchMenuItem;

    private FlickrApiParams params;

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

    /**
     * This method is responsible for initializing the views and data members of this class
     */
    public void initViews() {

            ///Initializing params with default values
            params = new FlickrApiParams("en", "json", 1, "");

            //This following lines sets up the loading view.
            progressView = Common.getProgress();
            progressView.setCancelable(false);

            //The following function call is responsible to fetch the Flickr Feeds.
            fetchFlickrFeed();

            //Change the search once the tag is entered.
            searchInput.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    //Set the icon of search menu item.
                    searchMenuItem.setIcon(getResources().getDrawable(R.drawable.ic_check_white_24dp));
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }

    /**
     * This function is responsible of fetching the fickr feeds and initializing the adapter for gallery recycler.
     */
    public void fetchFlickrFeed() {

            //Show loading view.
            progressView.show(MainActivity.this.getSupportFragmentManager(), "");

        FlickrFeedBAL.getFlickrFeed(params, new FeedFetchListener() {
            @Override
            public void onFeedFetched(FlickrFeedResponse response) {

                adapter = new GalleryRecyclerAdapter(MainActivity.this, response);
                recycler.setLayoutManager(getLayoutManager());
                recycler.setAdapter(adapter);
                progressView.dismiss();
                Common.showSnackBarLong(coordinatorLayout, getString(R.string.success_message));

            }

            @Override
            public void onFeedNotFetched() {
                Common.showSnackBarLong(coordinatorLayout, getString(R.string.fees_not_fetched_message));
            }

            @Override
            public void onNetworkFailure() {
                Common.showSnackBarLong(coordinatorLayout, getString(R.string.network_failure_message));
            }

            @Override
            public void onResponseIssue() {
                Common.showSnackBarLong(coordinatorLayout, getString(R.string.response_issue_messgae));
            }

            @Override
            public void onRequestTimeout() {
                Common.showSnackBarLong(coordinatorLayout, getString(R.string.request_timeout_message));
            }

            @Override
            public void onException(String errorMessage) {
                Common.showSnackBarLong(coordinatorLayout, errorMessage);

            }
        });
    }

    /**
     * This method is responsible for setting up the home toolbar.
     */
    private void setToolbar() {
            Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
            setSupportActionBar(toolbar);
        }

    /**
     * This method is responsible for setting up the GridLayoutManager for gallery recycler.
     * @return
     */
    public GridLayoutManager getLayoutManager() {
            layoutManager = new GridLayoutManager(MainActivity.this, 2, LinearLayoutManager.VERTICAL, false);
            return layoutManager;
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
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

            if(searchInput.getVisibility() == View.GONE) {
                appBarLogo.setVisibility(View.GONE);
                searchInput.setVisibility(View.VISIBLE);
            }else{
                params.setTags(searchInput.getText().toString());
                fetchFlickrFeed();
            }

            return true;
        } else if (id == R.id.order_date_taken){

            //Sorting the recycler data set based on date taken.
            adapter.mDataSet.setItems(Common.sortByDateTaken(adapter.mDataSet.getItems()));
            adapter.notifyDataSetChanged();

        }else if(id == R.id.order_date_published){

            //Sorting the recycler data set based on date published.
            adapter.mDataSet.setItems(Common.sortByDatePublished(adapter.mDataSet.getItems()));
            adapter.notifyDataSetChanged();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        //Check if search input text is visible.
        if(searchInput.getVisibility() == View.VISIBLE){
            searchInput.setVisibility(View.GONE);
            appBarLogo.setVisibility(View.VISIBLE);
            searchMenuItem.setIcon(getResources().getDrawable(R.drawable.ic_search_white_24dp));

        }
        else{
            super.onBackPressed();
        }
    }
}
