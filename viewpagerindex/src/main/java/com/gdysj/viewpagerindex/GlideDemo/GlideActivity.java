package com.gdysj.viewpagerindex.GlideDemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gdysj.viewpagerindex.R;

import jp.wasabeef.glide.transformations.CropCircleTransformation;

public class GlideActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Button btn_load_image;

    private static final String URL = "http://www.bz55.com/uploads/allimg/121109/1-121109101924.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        btn_load_image = (Button) findViewById(R.id.btn_load_image);
        mImageView = (ImageView) findViewById(R.id.image_view);

        btn_load_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadImage();
            }
        });
    }

    private void loadImage(){
        Glide.with(this)
                .load(URL)
                .bitmapTransform(new CropCircleTransformation(this))
                .into(mImageView);
    }
}
