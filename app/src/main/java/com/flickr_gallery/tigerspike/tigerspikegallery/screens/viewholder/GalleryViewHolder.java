package com.flickr_gallery.tigerspike.tigerspikegallery.screens.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Netaq on 4/13/2017.
 */

public class GalleryViewHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.thumbnail_image_view)
    public ImageView thumbnailImage;

    public GalleryViewHolder(View itemView) {

        super(itemView);
        ButterKnife.bind(this,itemView);

    }
}
