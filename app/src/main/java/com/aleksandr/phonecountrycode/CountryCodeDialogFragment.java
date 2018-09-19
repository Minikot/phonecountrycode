package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aleksandr.phonecountrycode.model.CountryCode;

import java.util.ArrayList;

public class CountryCodeDialogFragment extends android.support.v4.app.DialogFragment{

    EditText etFilterCodeD;
    RecyclerView rvCodeListD;
    CountryCodeAdapter adapterD;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_country_code, container, false);

        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.YourCustomeThemeName);

        etFilterCodeD = view.findViewById(R.id.et_filter_code_dialog_fragment);
        rvCodeListD = view.findViewById(R.id.rv_phone_code_dialog_fragment);
        adapterD = new CountryCodeAdapter(new ArrayList<CountryCode>(), getFragmentManager(), getContext());
        rvCodeListD.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCodeListD.setAdapter(adapterD);

        return view;
    }

}
