package com.aleksandr.phonecountrycode.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.aleksandr.phonecountrycode.MainFragment;
import com.aleksandr.phonecountrycode.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class CountryCodeBL{

    private int resId;
    private String code;
    private String jsonData;

    private static CountryCodeBL instance = null;

    public static CountryCodeBL getInstance() {
        if (instance == null) {
            instance = new CountryCodeBL();
        }
        return instance;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public List<CountryCode> countryWorkList;

    private ArrayList<CountryCode> codesArrayFiltered = new ArrayList<>();

    public ArrayList<CountryCode> getCodesArrayFiltered() {
        return codesArrayFiltered;
    }

    public void loadGradle(Context appContext) {
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
    }

    public void getCountries() {
        Gson gson = new Gson();
        String jsonOutput = jsonData;
        Type listType = new TypeToken<List<CountryCode>>() {
        }.getType();
        countryWorkList = gson.fromJson(jsonOutput, listType);
    }

    public void filterListCodesArray(String st) {
        boolean isContain;
        if (st == null) {
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

//    public TextWatcher getTextWatcher(   -   Как TextWatcher можно использовать из этого класса?

    public void backTo(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(MainFragment.CODE, getCode());
        args.putInt(MainFragment.RES_ID, getResId());
        mainFragment.setArguments(args);

        ft.replace(R.id.container, mainFragment, "mainFragment");
        ft.addToBackStack(null);
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.commit();
    }
}


