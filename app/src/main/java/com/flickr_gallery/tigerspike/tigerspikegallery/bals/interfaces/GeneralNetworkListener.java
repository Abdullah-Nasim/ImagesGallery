package com.flickr_gallery.tigerspike.tigerspikegallery.bals.interfaces;

/**
 * Created by Netaq on 4/13/2017.
 */

public interface GeneralNetworkListener {
    void onNetworkFailure();
    void onResponseIssue();
    void onRequestTimeout();
    void onException(String errorMessage);
}
