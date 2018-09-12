package com.aleksandr.phonecountrycode.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;

import com.aleksandr.phonecountrycode.CountryCodeAdapter;
import com.aleksandr.phonecountrycode.MainFragment;
import com.aleksandr.phonecountrycode.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CountryCodeBL implements CountryCodeDAO {

    private int resId;
    private String code = null;

    private static CountryCodeBL instance = null;
    public static CountryCodeBL getInstance() {
        if (instance == null) {
            instance = new CountryCodeBL();
        }
        return instance;
    }

    public ArrayList<CountryCode> codesArrayFiltered = new ArrayList<>();

    public ArrayList<CountryCode> getCodesArrayFiltered() {
        return codesArrayFiltered;
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

    public void getFilterListCodes(Context appContext, String str) {
        filterListCodes(jsonData, str);
    }

    private String jsonData;

    //Метод вызывается при старте второго фрагмента, считывает данные и помещает в стрингу.
    // Метод filterListCodes(String json, String st) будет обращаться к переменной, а не постоянно к данным во время фильтрации списка.
    public void loadGradle(Context appContext) {

        Resources res = appContext.getResources();
        InputStream is = res.openRawResource(R.raw.e164_country_codes);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append((scanner.nextLine()));
        }
        jsonData = builder.toString();
    }

    private String parseJsonCountryCode(String json) {
        StringBuilder builder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject countryCodes = root.getJSONObject("code");
            JSONArray lists = countryCodes.getJSONArray("list");

            for (int i = 0; i < lists.length(); i++) {
                JSONObject list = lists.optJSONObject(i);
                builder.append(list.getString("name"))
                        .append(": ")
                        .append(list.getInt("code"))
                        .append(": ")
                        .append(list.getString("digits"))
                        .append(": ")
                        .append(list.getString("iso_3166_1"))
                        .append("\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public void filterListCodes(String json, String st) {

        try {
            JSONObject root = new JSONObject(json);
            JSONObject countryCodes = root.getJSONObject("code");
            JSONArray lists = countryCodes.getJSONArray("list");

            boolean isContain = true;

            if (st == null) {
                for (int i = 0; i < lists.length(); i++) {
                    codesArrayFiltered.add(new CountryCode(
                            lists.optJSONObject(i).getString("name"),
                            lists.optJSONObject(i).getInt("code"),
                            0,
                            lists.optJSONObject(i).getString("iso_3166_1")));
                }

            } else if (st != null) {
                for (int i = 0; i < lists.length(); i++) {
                    try {
                        int num = Integer.parseInt(st);
                        isContain = lists.optJSONObject(i).getString("code").contains(String.valueOf(num));
                        System.out.println("It is a number");

                    } catch (NumberFormatException e) {
                        isContain = lists.optJSONObject(i).getString("name").toLowerCase().contains(st.toLowerCase());
                        System.out.println("It is no a number");
                    }

                    if (isContain) {
                        codesArrayFiltered.add(new CountryCode(
                                lists.optJSONObject(i).getString("name"),
                                lists.optJSONObject(i).getInt("code"),
                                0,
                                lists.optJSONObject(i).getString("iso_3166_1")));
                    } else {

                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public TextWatcher getTextWatcher(final CountryCodeAdapter adapter, final Context appContext, final String str) {

        TextWatcher textWatcher = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codesArrayFiltered.removeAll(codesArrayFiltered);
                adapter.dataUpdate(getCodesArrayFiltered());
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    System.out.println("ZERO");
                    getFilterListCodes(appContext, str);
                    adapter.dataUpdate(codesArrayFiltered);

                } else {
                    System.out.println("FUUUUUUUCK");
                    getFilterListCodes(appContext, str);
                    adapter.dataUpdate(codesArrayFiltered);
                }
            }
        };
        return textWatcher;
    }

    public void dackTo(FragmentManager fragmentManager) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(MainFragment.CODE, getCode());
        args.putInt(MainFragment.RES_ID, resId);
        mainFragment.setArguments(args);

        ft.replace(R.id.container, mainFragment, "mainFragment");
        ft.addToBackStack(null);
        ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
        ft.commit();
    }
}
