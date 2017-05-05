package com.gdysj.otherdemo.search;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;

import com.gdysj.otherdemo.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;

public class SearchViewActivity extends AppCompatActivity {
    private static final String TAG = "searchView";

    private SearchView search_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        search_view = (SearchView) findViewById(R.id.search_view);

        search_view.setIconified(false);
        search_view.setQueryHint("请输入单号");
        search_view.setIconifiedByDefault(true);

//        int magId = getResources().getIdentifier("android:id/search_button", null, null);
//        ImageView magImage = (ImageView) search_view.findViewById(magId);
//        magImage.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
//        hideCloseSearchIcon(search_view);
        search_view.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Log.i(TAG, "on close");
                return false;
            }
        });

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.i(TAG, "onQueryTextSubmit " + query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.i(TAG, "onQueryTextChange " + newText);
                return false;
            }
        });

        EditText editText = (EditText) findViewById(R.id.edit);
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 35);


        gson();
    }

    private void hideCloseSearchIcon(SearchView searchView) {
        SearchView.SearchAutoComplete mQueryTextView = (SearchView.SearchAutoComplete)getFieldByReflect(
                "mQueryTextView", SearchView.class, searchView);
        mQueryTextView.setHint("dddd");
    }

    public static Object getFieldByReflect(String fieldName, Class<?> claz, Object target) {
        try {
            Field searchField = claz.getDeclaredField(fieldName);
            searchField.setAccessible(true);
            return searchField.get(target);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    private String source = "{\"buy_user_info\": \"{\"bank_no\":\"622568******1326\",\"ref_no\":\"031032102503\"}\", \"errMsg\": \"支付成功\"}";


    private void gson(){
        try {

            String sourcStr = changeCharset(source, "UTF-8");
            Log.i(TAG, "sourceStr " + sourcStr);

            Gson gson = new GsonBuilder()
    //                .registerTypeAdapter(InfoBean.class, new UserInfoJsonDeserializer())
    //                .excludeFieldsWithoutExposeAnnotation()
                    .create();

            String json = sourcStr.substring(0,sourcStr.length()).replaceAll("\\\\","");
            Log.i(TAG, "json " + json);

            String jsonStrng = gson.toJson(sourcStr);

//            InfoBean bean = JSON.parseObject(json, InfoBean.class);

////            InfoBean bean = gson.fromJson(json, InfoBean.class);
//            if (bean != null){
//                Log.i(TAG, " info bean " + bean.toString());
//            } else {
//                Log.i(TAG, " info bean is null");
//            }

            } catch (Exception e) {
                e.printStackTrace();
                Log.e(TAG, "解析出错 " + e.getMessage());
            }

    }

    public String changeCharset(String str, String newCharset)
            throws UnsupportedEncodingException {
        if (str != null) {
            //用默认字符编码解码字符串。
            byte[] bs = str.getBytes();
            //用新的字符编码生成字符串
            return new String(bs, newCharset);
        }
        return null;
    }
}

