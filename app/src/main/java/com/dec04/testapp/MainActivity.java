package com.dec04.testapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.GridView;

import com.dec04.testapp.Utils.GridViewAdapter;
import com.dec04.testapp.Utils.Http;
import com.dec04.testapp.Utils.LoadImage;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    private String[] urls;
    private GridView gridView;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gridView = findViewById(R.id.gridview);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setRefreshing(true);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                GridViewAdapter gridViewAdapter = (GridViewAdapter) gridView.getAdapter();
                gridViewAdapter.setBitmaps(new ArrayList<Bitmap>());
                gridViewAdapter.notifyDataSetChanged();

                getImages();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        getImages();
    }

    private void getImages() {
        String imagesArrayUrl = "http://dev-tasks.alef.im/task-m-001/list.php";

        Http.get(imagesArrayUrl, new RequestParams(), new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                urls = new String[response.length()];

                for(int i = 0; i < response.length(); i++) {
                    try {
                        urls[i] = response.get(i).toString();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                new LoadImage(MainActivity.this, gridView).execute(urls);

            }

        });
    }
}
