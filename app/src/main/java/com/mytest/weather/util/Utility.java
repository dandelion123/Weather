package com.mytest.weather.util;

import android.text.TextUtils;
import android.util.Log;

import com.mytest.weather.model.City;
import com.mytest.weather.model.County;
import com.mytest.weather.model.Province;
import com.mytest.weather.model.WeatherDB;

public class Utility {

    /**
     * 解析和处理服务器返回的省级数据
     *
     * @param weatherDB
     * @param response
     * @param type
     * @return
     */
    public synchronized static boolean handleResponse(WeatherDB weatherDB, String response, String type,int code) {
        if (!TextUtils.isEmpty(response)) {
            return XMLParser.parser(weatherDB, response, type ,code);
//            String[] allProvinces = response.split(",");
//            if (allProvinces != null && allProvinces.length > 0) {
//                for (String p : allProvinces) {
//                    String [] array=p.split("\\|");
//                    Province province=new Province();
//                    province.setProvinceCode(array[0]);
//                    province.setProvinceName(array[1]);
//                    weatherDB.saveProvince(province);
//                }
//                return true;
//            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     *
     * @param weatherDB
     * @param response
     * @param provinceId
     * @return

    public synchronized static boolean handleCityResponse(WeatherDB weatherDB, String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            //XMLParser.parserProvinces()
//            String[] allCities = response.split(",");
//            if (allCities != null && allCities.length > 0) {
//                for (String c : allCities) {
//                    String [] array=c.split("\\|");
//                    City city=new City();
//                    city.setProvinceId(provinceId);
//                    city.setCityCode(array[0]);
//                    city.setCityName(array[1]);
//                    weatherDB.saveCity(city);
//                }
//                return true;
//            }
        }
        return false;
    }

    public synchronized static boolean handleCountyResponse(WeatherDB weatherDB, String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0) {
                for (String c : allCounties) {
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    weatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }*/
}
