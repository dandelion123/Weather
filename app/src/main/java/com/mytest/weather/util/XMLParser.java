package com.mytest.weather.util;

import android.util.Log;

import com.mytest.weather.model.City;
import com.mytest.weather.model.County;
import com.mytest.weather.model.Province;
import com.mytest.weather.model.WeatherDB;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

public class XMLParser {

    public static boolean parser(WeatherDB weatherDB, String response, String type, int code) {
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(response));
            int eventType = xmlPullParser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String node = xmlPullParser.getName();
                if (eventType == XmlPullParser.START_TAG && "city".equals(node)) {
                    if ("province".equals(type)) {
                        Province province = new Province();
                        province.setProvinceName(xmlPullParser.getAttributeValue(null, "quName"));
                        province.setProvinceCode(xmlPullParser.getAttributeValue(null, "pyName"));
                        weatherDB.saveProvince(province);
                    } else if ("city".equals(type)) {
                        City city = new City();
                        city.setCityName(xmlPullParser.getAttributeValue(null, "cityname"));
                        city.setCityCode(xmlPullParser.getAttributeValue(null, "pyName"));
                        city.setProvinceId(code);
                        weatherDB.saveCity(city);
                    } else if ("county".equals(type)) {
                        County county = new County();
                        county.setCountyName(xmlPullParser.getAttributeValue(null, "cityname"));
                        county.setCountyCode(xmlPullParser.getAttributeValue(null, "pyName"));
                        county.setCityId(code);
                        weatherDB.saveCounty(county);
                    }
                }
                eventType = xmlPullParser.next();
            }
            return true;
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
