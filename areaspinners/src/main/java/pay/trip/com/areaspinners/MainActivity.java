package pay.trip.com.areaspinners;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner_province;
    private Spinner spinner_city;
    private Spinner spinner_district;

    // 所有省
    private String[] mProvince;
    // 省-市
    private Map<String, String[]> mCitisMap = new HashMap<>();
    // 市-区
    private Map<String, String[]> mDistrictMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
        updateSpinner();

    }

    private void initView(){
        spinner_province = (Spinner) findViewById(R.id.spinner_province);
        spinner_city = (Spinner) findViewById(R.id.spinner_city);
        spinner_district = (Spinner) findViewById(R.id.spinner_district);
    }

    private void initData(){
        initJsonData();
    }

    private void updateSpinner(){
        final List<String> provincesList = initProvinces();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, provincesList);
        spinner_province.setAdapter(adapter);
        spinner_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("spinner", "provinces select position " + position + " name " + provincesList.get(position));
                updateCitySpinner(provincesList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void updateCitySpinner(String provincesName){
        final List<String> citesList = initCityListBaseProvince(provincesName);
        ArrayAdapter<String> cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, citesList);
        spinner_city.setAdapter(cityAdapter);
        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("spinner", "city select position " + position + " name " + citesList.get(position));
                updateDistrictSpinner(citesList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void updateDistrictSpinner(String cityName){
        final List<String> districtList = initDistrictListBaseCity(cityName);
        if (districtList != null && districtList.size() > 0){
            ArrayAdapter<String> districtAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, districtList);
            spinner_district.setAdapter(districtAdapter);
            spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Log.i("spinner", "district select position " + position + " name " + districtList.get(position));
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void initJsonData(){
        try {
            StringBuilder sb = new StringBuilder();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("assets/" + "city.json");
            int len = -1;
            byte[] buf = new byte[1024];
            while ((len = inputStream.read(buf)) != -1){
                sb.append(new String(buf, 0, len, "utf-8"));
            }
            inputStream.close();
            dealJsonDate(new JSONArray(sb.toString()));
            Log.i("spinner", "date " + sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void dealJsonDate(JSONArray originalJsonData){

        try {
            mProvince = new String[originalJsonData.length()];
            for (int i = 0; i < originalJsonData.length(); i++){
                JSONObject jsonProvince = originalJsonData.getJSONObject(i);
                String provinceName = jsonProvince.getString("name");
                mProvince[i] = provinceName;

                // 解析城市的数据
                JSONArray jsonArrayCity = null;
                try {
                    jsonArrayCity = jsonProvince.getJSONArray("sub");
                } catch (JSONException e) {
                    e.printStackTrace();
                    continue;
                }
                String[] citiesData = new String[jsonArrayCity.length()];
                for (int j = 0; j < jsonArrayCity.length(); j++ ){
                    JSONObject jsonCity = jsonArrayCity.getJSONObject(j);
                    String cityName = jsonCity.getString("name");   // 获取城市名
                    citiesData[j] = cityName;

                    // 解析城区的数据
                    JSONArray jsonArrayDistrict = null;
                    try {
                        jsonArrayDistrict = jsonCity.getJSONArray("sub");
                    } catch (JSONException e) {
                        e.printStackTrace();
                        continue;
                    }
                    String[] districtData = new String[jsonArrayDistrict.length()];
                    for (int k = 0; k < jsonArrayDistrict.length(); k++){
                        JSONObject jsonDistrict = jsonArrayDistrict.getJSONObject(k);
                        String districtName =  jsonDistrict.getString("name");
                        districtData[k] = districtName;
                        Log.i("spinner", " district name " + districtName);
                    }
                    // 存放城区数据
                    mDistrictMap.put(cityName, districtData);
                    Log.i("spinner", " city name " + cityName);
                }
                // 存放城市数据
                mCitisMap.put(provinceName, citiesData);
                Log.i("spinner", " province name " + provinceName);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // 初始化省份
    private List<String> initProvinces(){
        List<String> provinceList = new ArrayList<>(mProvince.length);
        for (int i = 0; i < mProvince.length; i++){
            provinceList.add(mProvince[i]);
        }
        return provinceList;
    }

    /**
     *  根据省份得到相应的城市
     * @param provincesName 省份名称
     * @return
     */
    private List<String> initCityListBaseProvince(String provincesName){
        String[] cites = mCitisMap.get(provincesName);
        List<String> cityList = new ArrayList<>(cites.length);
        for (int i = 0; i < cites.length; i++){
            cityList.add(cites[i]);
        }

        return cityList;
    }

    /**
     *  根据城市得到相应的城区
     * @param cityName  城市名称
     * @return
     *
     */
    private List<String> initDistrictListBaseCity(String cityName){
        String[] districts = mDistrictMap.get(cityName);
        if (districts == null){
            return Collections.emptyList();
        }

        List<String> districtList = new ArrayList<>(districts.length);
        for (int i = 0; i < districts.length; i++){
            districtList.add(districts[i]);
        }
        return districtList;
    }


}
