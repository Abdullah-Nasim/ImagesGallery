package com.flickr_gallery.tigerspike.tigerspikegallery.screens.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 4/13/2017.
 */

public class GalleryViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.thumbnail_image_view)
    public ImageView thumbnailImage;

    @BindView(R.id.image_name)
    public TextView title;

    @BindView(R.id.image_taken_date)
    public  TextView taken_date;

    @BindView(R.id.image_published_date)
    public  TextView published_date;

    public GalleryViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this,itemView);

    }
}
