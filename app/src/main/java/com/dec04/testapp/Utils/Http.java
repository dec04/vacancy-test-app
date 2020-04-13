package com.dec04.testapp.Utils;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

/**
 * Class to work with http requests
 */
public class Http {

    private static AsyncHttpClient client = new AsyncHttpClient();

    /**
     * Create a GET request
     *
     * @param url - url
     * @param params - parameters to need include to request
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Create a POST request
     *
     * @param url - url
     * @param params - parameters to need include to request
     */
    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * Setting up persistent cookie store
     *
     * @param cookieStore - cookie store
     */
    public static void setCookieStore(PersistentCookieStore cookieStore) {
        client.setCookieStore(cookieStore);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return relativeUrl;
    }
}
