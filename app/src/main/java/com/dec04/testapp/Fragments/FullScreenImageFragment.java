package com.dec04.testapp.Fragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.dec04.testapp.R;

public class FullScreenImageFragment extends Fragment {

    private Bitmap bitmap;

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.full_screen_image_view, container, false);
        ImageView iv = rootView.findViewById(R.id.fullScreenImage_imageView);
        iv.setImageBitmap(bitmap);

        return rootView;
    }
}

