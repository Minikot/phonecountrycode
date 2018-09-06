package com.aleksandr.phonecountrycode.model;

import android.content.Context;
import android.text.TextWatcher;

import com.aleksandr.phonecountrycode.CountryCodeAdapter;

import java.util.ArrayList;

public interface CountryCodeDAO {

    String loadGradle2(Context appContext);

//    ArrayList<CountryCode> getCountryCodesArray(Context appContext);

    ArrayList<CountryCode> getCodesArrayFiltered();

    void getFilterListCodes(Context appContext, String str);

    TextWatcher getTextWatcher(CountryCodeAdapter adapter, final Context appContext, final String str);

}
