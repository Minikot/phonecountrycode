package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aleksandr.phonecountrycode.model.CountryCodeBL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        MainFragment mainFragment = new MainFragment();
        ft.add(R.id.container, mainFragment, "mainFragment");
        ft.commit();

        CountryCodeBL.getInstance().loadGradle(this);
        CountryCodeBL.getInstance().getCountries();
    }
}
