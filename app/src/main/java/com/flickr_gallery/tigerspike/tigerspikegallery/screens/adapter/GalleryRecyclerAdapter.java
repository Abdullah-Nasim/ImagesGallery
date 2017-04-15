package com.flickr_gallery.tigerspike.tigerspikegallery.screens.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;
import com.flickr_gallery.tigerspike.tigerspikegallery.models.FlickrFeedResponse;
import com.flickr_gallery.tigerspike.tigerspikegallery.screens.ImagePreviewActivity;
import com.flickr_gallery.tigerspike.tigerspikegallery.screens.viewholder.GalleryViewHolder;
import com.flickr_gallery.tigerspike.tigerspikegallery.utils.Common;

/**
 * Created by Abdullah on 4/13/2017.
 * Email: abdullahdxbid@gmail.com
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        GalleryViewHolder vh = (GalleryViewHolder)holder;

        Common.setThumbnailImage(context,mDataSet.getItems().get(position).getMedia().getM(),vh.thumbnailImage);

        vh.title.setText(mDataSet.getItems().get(position).getTitle());

        vh.taken_date.setText("Taken Date and Time: " + mDataSet.getItems().get(position).getDate_taken().split("T")[0] + "  " + mDataSet.getItems().get(position).getDate_taken().substring(mDataSet.getItems().get(position).getDate_taken().indexOf('T')+1,mDataSet.getItems().get(position).getDate_taken().lastIndexOf('-')));

        vh.published_date.setText("Published Date and Time: " + mDataSet.getItems().get(position).getPublished().split("T")[0] + "  " + mDataSet.getItems().get(position).getPublished().substring(mDataSet.getItems().get(position).getPublished().indexOf('T')+1,mDataSet.getItems().get(position).getPublished().lastIndexOf('Z')));

        vh.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImagePreviewActivity.class);
                intent.putExtra("ImageInfo",mDataSet.getItems().get(position));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mDataSet.getItems().size();
    }
}
