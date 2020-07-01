package com.example.triviaapp.controller;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class appController extends Application {

        private static appController instance;
        private RequestQueue requestQueue;

        public static final String TAG =appController.class.getSimpleName();



        public static synchronized appController getInstance() {
            if (instance == null) {
                instance = new appController();
            }
            return instance;
        }

    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public RequestQueue getRequestQueue() {
            if (requestQueue == null) {
                // getApplicationContext() is key, it keeps you from leaking the
                // Activity or BroadcastReceiver if someone passes one in.
                requestQueue = Volley.newRequestQueue(getApplicationContext());
            }
            return requestQueue;
        }

        public <T> void addToRequestQueue(Request<T> req,String tag) {
            req.setTag(tag);
            getRequestQueue().add(req);
        }
    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    public void cancelPendingRequests(Object tag)
    {
        if(requestQueue!=null)
            requestQueue.cancelAll(tag);
    }


}
