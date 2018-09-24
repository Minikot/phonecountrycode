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
    private static boolean isContain;
    private static List<CountryCode> countryWorkList;

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
        countryWorkList = gson.fromJson(jsonOutput, listType);
    }

    public static void filterListCodesArray(String st) {
        if (st.length() == 0) {
            for (int i = 0; i < countryWorkList.size(); i++) {
                codesArrayFiltered.add(new CountryCode(
                        countryWorkList.get(i).getName(),
                        countryWorkList.get(i).getCode(),
                        countryWorkList.get(i).getDigits(),
                        countryWorkList.get(i).getIso()));
            }
        } else {
            for (int i = 0; i < countryWorkList.size(); i++) {
                try {
                    int num = Integer.parseInt(st);
                    String code = String.valueOf(countryWorkList.get(i).getCode());
                    isContain = code.contains(st);

                } catch (NumberFormatException e) {
                    String name = countryWorkList.get(i).getName().toLowerCase();
                    isContain = name.contains(st.toLowerCase());
                }

                if (isContain) {
                    codesArrayFiltered.add(
                            new CountryCode(
                                    countryWorkList.get(i).getName(),
                                    countryWorkList.get(i).getCode(),
                                    countryWorkList.get(i).getDigits(),
                                    countryWorkList.get(i).getIso()));
                }
            }
        }
    }
}


