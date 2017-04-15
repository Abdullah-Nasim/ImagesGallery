package com.flickr_gallery.tigerspike.tigerspikegallery.network;

import com.flickr_gallery.tigerspike.tigerspikegallery.api.EndPoint;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abdullah on 4/13/2017.
 * Email: abdullahdxbid@gmail.com
 */

public class RestClient {

    private static ServicesInterface servicesInterface;
    private static Retrofit retrofit;

    static {

        //Configuring the Rest Client
        setUpRestClient();
    }

    private static void setUpRestClient() {

        retrofit = new Retrofit.Builder()
                .baseUrl(EndPoint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        servicesInterface = retrofit.create(ServicesInterface.class);


    }

    public static ServicesInterface getAdapter(){
        return servicesInterface;
    }

    public static Retrofit getRetrofit(){
        return retrofit;
    }
}
