package com.dec04.testapp.Utils;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;


import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.dec04.testapp.Fragments.FullScreenImageFragment;
import com.dec04.testapp.R;

import java.util.ArrayList;
import java.util.List;

public final class GridViewAdapter extends BaseAdapter {
    private List<Bitmap> bitmaps = new ArrayList<Bitmap>();
    private LayoutInflater mInflater;
    private Context context;

    public GridViewAdapter(Context context, List<Bitmap> list) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        bitmaps = list;
    }

    public void setBitmaps(List<Bitmap> bitmaps) {
        this.bitmaps = bitmaps;
    }

    @Override
    public int getCount() {
        return bitmaps.size();
    }

    @Override
    public Bitmap getItem(int i) {
        return bitmaps.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ImageView imageView;
        final Bitmap bitmapImage = getItem(i);

        if (v == null) {
            v = mInflater.inflate(R.layout.image_item_view, viewGroup, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
        }

        imageView = (ImageView) v.getTag(R.id.picture);
        imageView.setImageBitmap(bitmapImage);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FullScreenImageFragment fragment = new FullScreenImageFragment();
                fragment.setBitmap(bitmapImage);

                FragmentManager fm = ((FragmentActivity)context).getFragmentManager();
                FragmentTransaction transaction = fm.beginTransaction();
                transaction.replace(R.id.root_constraintLayout, fragment );
                transaction.addToBackStack("fullScreenImage");
                transaction.commit();
            }
        });

        return v;
    }
}
