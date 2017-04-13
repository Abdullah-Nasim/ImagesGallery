package com.flickr_gallery.tigerspike.tigerspikegallery.bals.interfaces;

import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;

/**
 * Created by Netaq on 4/13/2017.
 */

public interface FeedFetchListener extends GeneralNetworkListener{

    void onFeedFetched(FlickrFeedResponse response);
    void onFeedNotFetched();
}
