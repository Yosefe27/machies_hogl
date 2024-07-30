package com.retrotech.machies_hogl.utils;

import android.content.Context;
import android.widget.ImageView;

import com.retrotech.machies_hogl.R;
import com.squareup.picasso.Picasso;


/**
 * Created by Harold King on 28/05/2019
 */
public class PicassoClient {

    //DOWNLOAD AND CACHE IMG

    public static void loadImage(Context c, String url, ImageView img)
    {
        if(url != null && url.length()>0)
        {
            Picasso.get().load(url).placeholder(R.drawable.ic_user).into(img);
        }else {
            Picasso.get().load(R.drawable.ic_user).into(img);

        }
    }






    //DOWNLOAD AND CACHE IMG


}
