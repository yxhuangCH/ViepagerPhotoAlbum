package com.gdysj.viewpagerindex.PhotoSelect;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.gdysj.viewpagerindex.R;
import com.soundcloud.android.crop.Crop;

import java.io.File;

public class PhotoSelectActivity extends AppCompatActivity {

    private Button btn_select;
    private ImageView iv_select;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_select);

        btn_select = (Button) findViewById(R.id.btn_select);
        iv_select = (ImageView) findViewById(R.id.iv_select);

        btn_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_select.setImageDrawable(null);
                Crop.pickImage(PhotoSelectActivity.this);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent result) {
        if (requestCode == Crop.REQUEST_PICK && resultCode == RESULT_OK){
            beginCrop(result.getData());
        } else if (requestCode == Crop.REQUEST_CROP){
            handleCrop(resultCode, result);
        }
    }

    private void beginCrop(Uri source){
        Uri destination = Uri.fromFile(new File(getCacheDir(), "cropped"));
        Crop.of(source, destination).asSquare().start(this);
    }

    private void handleCrop(int resultCode, Intent result){
        if (resultCode == RESULT_OK){
            iv_select.setImageURI(Crop.getOutput(result));
        } else if (resultCode == Crop.RESULT_ERROR){
            Toast.makeText(this, Crop.getError(result).getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
