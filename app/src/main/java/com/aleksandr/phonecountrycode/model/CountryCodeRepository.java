package com.aleksandr.phonecountrycode.model;

import android.content.Context;
import android.content.res.Resources;

import com.aleksandr.phonecountrycode.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CountryCodeRepository {

    private static String jsonData;
    private static List<CountryCode> countryFromJson;

    public static void getCountryFromJson(Context appContext) {
        Resources res = appContext.getResources();
        InputStream is = res.openRawResource(R.raw.e164_country_codes);

        try {
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonData = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson gson = new Gson();
        String jsonOutput = jsonData;
        Type listType = new TypeToken<List<CountryCode>>() {
        }.getType();
        countryFromJson = gson.fromJson(jsonOutput, listType);

        for (int i = 0; i < countryFromJson.size(); i++) {
            countryFromJson.get(i).setResId(appContext.getResources().
                    getIdentifier("ic_" + countryFromJson.get(i).getIso(), "drawable", appContext.getPackageName()));
            countryFromJson.get(i).setCodePlus(String.valueOf("+" + countryFromJson.get(i).getCode()));
        }
    }

    public static List<CountryCode> filterListCodesArray(String st, Context appContext) {
        ArrayList<CountryCode> filterCode = new ArrayList<>();
        if (countryFromJson == null) getCountryFromJson(appContext);

        if (st.length() == 0) {
            return countryFromJson;
        } else {
            for (int i = 0; i < countryFromJson.size(); i++) {
                if (countryFromJson.get(i).getCodePlus().concat(countryFromJson.get(i).getName()).toLowerCase().contains(st.toLowerCase())) {
                    filterCode.add(countryFromJson.get(i));
                }
            }
            return filterCode;
        }
    }
}