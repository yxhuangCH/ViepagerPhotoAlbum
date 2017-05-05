package com.gdysj.otherdemo.countView;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.gdysj.otherdemo.R;

public class CountViewActivity extends AppCompatActivity {

    private VerticalTextView tv_vertical;
    private CountTextView tv_count_view;
    private EditText edt_input;
    private Button btn_input;

    private AllCountView all_count_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_view);

        all_count_view = (AllCountView) findViewById(R.id.all_count_view);
        tv_count_view = (CountTextView) findViewById(R.id.tv_count_view);
        edt_input = (EditText) findViewById(R.id.edt_input);
        btn_input = (Button) findViewById(R.id.btn_input);

        tv_count_view.setText(26, Color.BLACK);//设置属性,具体跟踪源码
        tv_count_view.setAnimTime(300);//设置进入和退出的时间间隔

        btn_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = edt_input.getText().toString();
                tv_count_view.setCountText(string);
                all_count_view.setContent(string);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    protected void onPause() {
        super.onPause();

    }
}
