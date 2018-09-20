package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.aleksandr.phonecountrycode.model.CountryCode;
import com.aleksandr.phonecountrycode.model.CountryCodeRepository;

public class MainActivity extends AppCompatActivity implements CountryCodeDialogFragment.CodeSelectedListener {

    private MainFragment mainFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new MainFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
//        MainFragment mainFragment = new MainFragment();
        ft.add(R.id.container, mainFragment, "mainFragment");
        ft.commit();

        CountryCodeRepository.getInstance().loadGradle(this);
        CountryCodeRepository.getInstance().getCountries();
    }

    @Override
    public void onChangeCode(CountryCode countryCode) {
        if (mainFragment != null) {
            mainFragment.setCode(countryCode);
        }
    }
}
