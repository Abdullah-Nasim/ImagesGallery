package com.flickr_gallery.tigerspike.tigerspikegallery.network;

import com.flickr_gallery.tigerspike.tigerspikegallery.api.EndPoint;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Netaq on 4/13/2017.
 */

public interface ServicesInterface {


    @GET(EndPoint.EndPoint)
    Call<FlickrFeedResponse> getAllFeed(@Query("lang") String lang, @Query("format") String format, @Query("nojsoncallback") int nojsoncallback);

}
