package com.gdysj.otherdemo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;

/**
 * Author: yxhuang
 * Date: 2017/4/15
 * Email: yxhuang@gmail.com
 */

public class UserInfoJsonDeserializer implements JsonDeserializer<InfoBean> {

    @Override
    public InfoBean deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (json != null){
            InfoBean infoBean = new InfoBean();

            JsonObject object = json.getAsJsonObject();

            String errMsg = object.get("errMsg").getAsString();
            infoBean.setErrMsg(errMsg);

            String temp = object.get("buy_user_info").getAsString();
            String info = temp.substring(1, temp.length() - 1);  // 去除一头一尾的 “”;

            try {
                InfoBean.UserInfo userInfo = new InfoBean.UserInfo();
                JSONObject uerInfoObject = new JSONObject(info);

                String bank_no = uerInfoObject.optString("bank_no");
                userInfo.setBank_no(bank_no);

                String ref_no = uerInfoObject.optString("ref_no");
                userInfo.setRef_no(ref_no);


            } catch (JSONException e) {
                e.printStackTrace();
            }

            return new InfoBean();
        }

        return null;

    }
}
