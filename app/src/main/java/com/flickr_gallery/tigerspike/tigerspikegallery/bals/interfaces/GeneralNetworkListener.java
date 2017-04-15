package com.flickr_gallery.tigerspike.tigerspikegallery.bals.interfaces;

/**
 * Created by Abdullah on 4/13/2017.
 * Email: abdullahdxbid@gmail.com
 */

public interface GeneralNetworkListener {
    void onNetworkFailure();
    void onResponseIssue();
    void onRequestTimeout();
    void onException(String errorMessage);
}
