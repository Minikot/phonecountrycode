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

    private static ArrayList<CountryCode> codesArrayFiltered = new ArrayList<>();

    public static ArrayList<CountryCode> getCodesArrayFiltered() {
        return codesArrayFiltered;
    }

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
        }
    }

    public static void filterListCodesArray(String st) {
        if (st.length() == 0) {
            for (int i = 0; i < countryFromJson.size(); i++) {
                codesArrayFiltered.add(new CountryCode(
                        countryFromJson.get(i).getName(),
                        countryFromJson.get(i).getCode(),
                        countryFromJson.get(i).getDigits(),
                        countryFromJson.get(i).getIso(),
                        countryFromJson.get(i).getResId()));
            }
        } else {
            for (int i = 0; i < countryFromJson.size(); i++) {
                boolean isContain;
                try {
                    int num = Integer.parseInt(st);
                    String code = String.valueOf(countryFromJson.get(i).getCode());
                    isContain = code.contains(st);

                } catch (NumberFormatException e) {
                    String name = countryFromJson.get(i).getName().toLowerCase();
                    isContain = name.contains(st.toLowerCase());
                }

                if (isContain) {
                    codesArrayFiltered.add(
                            new CountryCode(
                                    countryFromJson.get(i).getName(),
                                    countryFromJson.get(i).getCode(),
                                    countryFromJson.get(i).getDigits(),
                                    countryFromJson.get(i).getIso(),
                                    countryFromJson.get(i).getResId()));
                }
            }
        }
    }
}


