package com.flickr_gallery.tigerspike.tigerspikegallery.bals;


import com.flickr_gallery.tigerspike.tigerspikegallery.api.FlickrApiParams;
import com.flickr_gallery.tigerspike.tigerspikegallery.bals.interfaces.FeedFetchListener;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.flickr_gallery.tigerspike.tigerspikegallery.network.RestClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Abdullah on 4/13/2017.
 * Email: abdullahdxbid@gmail.com
 */

public class FlickrFeedBAL {

    public static void getFlickrFeed(FlickrApiParams params, final FeedFetchListener listener){


        Call<FlickrFeedResponse> call = RestClient.getAdapter().getAllFeed(params.getLang(),params.getFormat(),params.getNojsoncallback(),params.getTags());

        call.enqueue(new Callback<FlickrFeedResponse>() {
            @Override
            public void onResponse(Call<FlickrFeedResponse> call, Response<FlickrFeedResponse> response) {

                //Check if response is successful
                if(response.isSuccessful()) {

                    //Check if response dose not contain empty array
                    if (response.body().getItems().size() != 0) {

                        //Flickr Feed successfully fetched
                        listener.onFeedFetched(response.body());
                    } else {
                        listener.onFeedNotFetched();
                    }

                } else {
                    listener.onResponseIssue();
                }

            }

            @Override
            public void onFailure(Call<FlickrFeedResponse> call, Throwable t) {

                //Check the type of Exception
                if(t instanceof IllegalArgumentException){

                    listener.onResponseIssue();

                } else {
                    listener.onNetworkFailure();
                }

                }
        });

    }
}
