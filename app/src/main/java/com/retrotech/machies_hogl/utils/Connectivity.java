package com.retrotech.machies_hogl.utils;
/**
 * Created by Harold King on 17/02/2020.
 */
import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Connectivity {

        private static Connectivity mInstance;
        private RequestQueue mRequestQueue;
        private static Context mCtx;

    private Connectivity(Context context) {
            mCtx = context;
            mRequestQueue = getRequestQueue();
        }

        public static synchronized Connectivity getInstance(Context context) {
            // If instance is not available, create it. If available, reuse and return the object.
            if (mInstance == null) {
                mInstance = new Connectivity(context);
            }
            return mInstance;
        }

        public RequestQueue getRequestQueue() {
            if (mRequestQueue == null) {
                // getApplicationContext() is key. It should not be activity context,
                // or else RequestQueue won't last for the lifetime of your app
                mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
            }
            return mRequestQueue;
        }

        public  void addToRequestQueue(Request req) {
            getRequestQueue().add(req);
        }

    }