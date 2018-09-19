package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aleksandr.phonecountrycode.model.CountryCode;
import com.aleksandr.phonecountrycode.model.CountryCodeRepository;

import java.util.ArrayList;

public class CountryCodeFragment extends Fragment {

    EditText etFilterText;
    RecyclerView rvCodeList;
    CountryCodeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);

        etFilterText = view.findViewById(R.id.et_filter_code_fragment);
        etFilterText.addTextChangedListener(getTextWatcher());

        CountryCodeRepository.getInstance().filterListCodesArray(etFilterText.getText().toString());

        rvCodeList = view.findViewById(R.id.rv_phone_code_fragment);
        adapter = new CountryCodeAdapter(new ArrayList<CountryCode>(), getFragmentManager(), getContext());
        rvCodeList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCodeList.setAdapter(adapter);

        adapter.dataUpdate(CountryCodeRepository.getInstance().getCodesArrayFiltered());
        return view;
    }

    public TextWatcher getTextWatcher() {

        TextWatcher textWatcher = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                CountryCodeRepository.getInstance().getCodesArrayFiltered().
                        removeAll(CountryCodeRepository.getInstance().
                                getCodesArrayFiltered());
            }

            public void afterTextChanged(Editable s) {
                    CountryCodeRepository.getInstance().filterListCodesArray(s.toString());
                    adapter.dataUpdate(CountryCodeRepository.getInstance().getCodesArrayFiltered());
            }
        };
        return textWatcher;
    }
}
