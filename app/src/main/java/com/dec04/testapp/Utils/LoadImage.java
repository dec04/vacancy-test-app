package com.dec04.testapp.Utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dec04.testapp.R;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class LoadImage extends AsyncTask<String, Void, List<Bitmap>> {

    private WeakReference<View> view;
    private WeakReference<Context> context;

    public LoadImage(Context context, View view) {
        this.context = new WeakReference<Context>(context) ;
        this.view = new WeakReference<View>(view);
    }

    protected List<Bitmap> doInBackground(String[] urls) {

        List<Bitmap> bitmapList = new ArrayList<>();

        for (String url : urls) {
            try {
                URL oldUrl = new URL(url);
                URL newUrl = new URL("https", oldUrl.getHost(), oldUrl.getPort(), oldUrl.getFile());

                InputStream in = newUrl.openStream();
                Bitmap b = BitmapFactory.decodeStream(in);

                if (b != null) {
                    bitmapList.add(b);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return bitmapList;
    }

    protected void onPostExecute(List<Bitmap> bitmapList) {

        ((GridView) view.get()).setAdapter(new GridViewAdapter(context.get(), bitmapList));

        SwipeRefreshLayout swipeRefreshLayout = ((ViewGroup) view.get().getParent()).findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(false);

    }
}
