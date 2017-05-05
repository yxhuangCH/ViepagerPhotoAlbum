package com.gdysj.otherdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView et_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_input = (TextView) findViewById(R.id.et_input);
        et_input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() < 6){
                    et_input.setError("这不是正确的");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Pattern z1_ = Pattern.compile("^(?=.*?[a-z])(?=.*?[0-9])[a-zA-Z0-9_]{6,16}$");

//        testOkHttp();

        TextView textView = (TextView) findViewById(R.id.text);

//        SpannableString spanString = new SpannableString("1551555");
//        AbsoluteSizeSpan span = new AbsoluteSizeSpan(getResources().getDimensionPixelOffset(R.dimen.sp_39));
//        spanString.setSpan(span, 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spanString.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorAccent)), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(getSizeColorSpanText(this, "485855.", R.dimen.sp_39, R.color.colorAccent));



    }

    private void testOkHttp(){
        final OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder()
                .get()
                .url("https://www.baidu.com/")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Response response = okHttpClient.newCall(request).execute();
                    Log.i("okHttp", "respone " + response);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    public static SpannableString getSizeColorSpanText(@NonNull Context context, @NonNull String txt, @DimenRes int sizeId, @ColorRes int colorId) {
        SpannableString spanString = new SpannableString(txt);
        if (context != null && !TextUtils.isEmpty(spanString)) {
            AbsoluteSizeSpan span = new AbsoluteSizeSpan(context.getResources().getDimensionPixelOffset(sizeId));
            spanString.setSpan(span, 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spanString.setSpan(new ForegroundColorSpan(context.getResources().getColor(colorId)), 0, spanString.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spanString;
    }



}
