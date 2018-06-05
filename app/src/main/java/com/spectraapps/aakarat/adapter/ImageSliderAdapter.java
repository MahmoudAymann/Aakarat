package com.spectraapps.aakarat.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.spectraapps.aakarat.R;

import java.util.ArrayList;

/**
 * Created by MahmoudAyman on 25/04/2018.
 */
public class ImageSliderAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;
    private ListAllListeners listAllListeners;

    public ImageSliderAdapter(Context context, ArrayList<Integer> images, ListAllListeners listAllListeners) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
        this.listAllListeners=listAllListeners;
    }
    public interface ListAllListeners {
        void onItemViewClick(ArrayList<Integer> images, int position);
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup view,final int position) {
        View myImageLayout = inflater.inflate(R.layout.single_image_slide_view, view, false);
        ImageView myImage = myImageLayout.findViewById(R.id.image);
        myImage.setImageResource(images.get(position));


        myImageLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAllListeners.onItemViewClick(images,position);
            }
        });

        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
