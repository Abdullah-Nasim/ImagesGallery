package com.flickr_gallery.tigerspike.tigerspikegallery.utils;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.flickr_gallery.tigerspike.tigerspikegallery.R;

/**
 * Created by Netaq on 4/14/2017.
 */


public class ProgressView extends DialogFragment {

    private String TAG = "ProgressView";
    public ProgressView() {
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Dialog dialog = new Dialog(getActivity());

        //Request a window without the title
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().getDecorView().setBackgroundColor(getResources().getColor(android.R.color.transparent));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View parent = inflater.inflate(R.layout.loader_view, null);
        parent.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        dialog.setContentView(parent);
        dialog.setCanceledOnTouchOutside(false);
        return dialog;

    }

    @Override
    public void show(FragmentManager manager, String tag) {

        if(this.isAdded())
        {
            return; //or return false/true, based on where you are calling from
        }
        FragmentTransaction ft = manager.beginTransaction();
        try {
            ft.add(this, TAG);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        /*try catch is used to tackle the crash
         when user close the app and some thing
         running in background of app*/
        try {
            ft.commitAllowingStateLoss();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void dismiss() {
        try {
            super.dismiss();
        }
        catch (IllegalStateException e)
        {
            super.dismissAllowingStateLoss();
        }
    }
}

