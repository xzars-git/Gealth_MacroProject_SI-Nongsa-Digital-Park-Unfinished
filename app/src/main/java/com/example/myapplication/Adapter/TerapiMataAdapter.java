package com.example.myapplication.Adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.myapplication.R;

public class TerapiMataAdapter extends PagerAdapter {
    Context context;

    int[] images = {

            R.drawable.mata1,
            R.drawable.mata2,
            R.drawable.mata3,
            R.drawable.mata4,
            R.drawable.mata5,
            R.drawable.mata6,
            R.drawable.mata7,
            R.drawable.mata8,

    };

    int[] headings = {

            R.string.mataHeadingText1,
            R.string.mataHeadingText2,
            R.string.mataHeadingText3,
            R.string.mataHeadingText4,
            R.string.mataHeadingText5,
            R.string.mataHeadingText6,
            R.string.mataHeadingText7,
            R.string.mataHeadingText8,



    };


    public TerapiMataAdapter(Context context) {

        this.context = context;

    }

    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_terapi_mata, container, false);

        ImageView slidetitleimage = (ImageView) view.findViewById(R.id.titleImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.titleText);

        slidetitleimage.setImageResource(images[position]);
        slideHeading.setText(headings[position]);

        container.addView(view);

        return view;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((LinearLayout) object);

    }
}

