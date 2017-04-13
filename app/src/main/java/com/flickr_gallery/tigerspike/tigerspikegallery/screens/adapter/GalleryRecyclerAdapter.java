package com.flickr_gallery.tigerspike.tigerspikegallery.screens.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.flickr_gallery.tigerspike.tigerspikegallery.screens.viewholder.GalleryViewHolder;
import com.flickr_gallery.tigerspike.tigerspikegallery.utils.Common;

/**
 * Created by Netaq on 4/13/2017.
 */

public class GalleryRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    public FlickrFeedResponse mDataSet;

    public GalleryRecyclerAdapter(Context context, FlickrFeedResponse mDataSet){

        this.context = context;
        this.mDataSet = mDataSet;
        
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder v1 = null;
        v1 = new GalleryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item,parent,false));
        return v1;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        GalleryViewHolder vh = (GalleryViewHolder)holder;

        Common.setThumbnailImage(context,mDataSet.getItems().get(position).getMedia().getM(),vh.thumbnailImage);
    }

    @Override
    public int getItemCount() {
        return mDataSet.getItems().size();
    }
}
