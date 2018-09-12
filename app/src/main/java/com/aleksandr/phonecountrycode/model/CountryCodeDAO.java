package com.aleksandr.phonecountrycode.model;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

public interface CountryCodeDAO {

    void loadGradle(Context appContext);

    ArrayList<CountryCode> getCodesArrayFiltered();

    void getFilterListCodes(Context appContext, String str);

    void dackTo(FragmentManager fragmentManager);

    void setCode(String code);
    String getCode();

    void setResId(int resId);
}
