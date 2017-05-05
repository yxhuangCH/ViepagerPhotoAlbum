package com.gdysj.otherdemo;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Author: yxhuang
 * Date: 2017/4/15
 * Email: yxhuang@gmail.com
 */

public class UserInfoTypeAdapter extends TypeAdapter<InfoBean> {
    @Override
    public void write(JsonWriter out, InfoBean value) throws IOException {
        if (value == null){
            out.nullValue();
        } else {

        }

    }

    @Override
    public InfoBean read(JsonReader in) throws IOException {
        return null;
    }
}
