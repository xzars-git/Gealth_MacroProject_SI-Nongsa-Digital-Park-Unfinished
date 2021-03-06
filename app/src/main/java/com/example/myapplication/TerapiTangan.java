package com.example.myapplication;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myapplication.Adapter.TerapiTanganAdapter;

public class TerapiTangan extends AppCompatActivity {

    LinearLayout mDotLayout;
    ImageButton nextbtn;
    ViewPager mSLideViewPager;

    TextView[] dots;
    TerapiTanganAdapter viewPagerAdapter;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terapi_tangan);

        nextbtn = findViewById(R.id.nextButton);


        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getitem(0) < 14)
                    mSLideViewPager.setCurrentItem(getitem(1), true);
                else {

                    Intent i = new Intent(TerapiTangan.this, SelesaiTerapi.class);
                    startActivity(i);
                    finish();

                }

            }
        });


        mSLideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        mDotLayout = (LinearLayout) findViewById(R.id.indicator_layout);

        viewPagerAdapter = new TerapiTanganAdapter(this);

        mSLideViewPager.setAdapter(viewPagerAdapter);

        setUpindicator(0);
        mSLideViewPager.addOnPageChangeListener(viewListener);
        getSupportActionBar().hide();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void setUpindicator(int position) {

        dots = new TextView[15];
        mDotLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {


            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#95"));
            dots[i].setTextSize(42);
            dots[i].setLetterSpacing(0.1F);
            dots[i].setTextColor(getResources().getColor(R.color.softGrey, getApplicationContext().getTheme()));
            mDotLayout.addView(dots[i]);

        }

        dots[position].setTextColor(getResources().getColor(R.color.darkGrey, getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i) {

        return mSLideViewPager.getCurrentItem() + i;
    }

    public void btnKembali(View view) {
        if (mSLideViewPager.getCurrentItem() != 0) {
            mSLideViewPager.setCurrentItem(mSLideViewPager.getCurrentItem() - 1, true);
        } else {
            finish();
        }
    }
}