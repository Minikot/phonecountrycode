package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.aleksandr.phonecountrycode.model.CodeFactory;
import com.aleksandr.phonecountrycode.model.CountryCode;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    EditText etFilterText;
    RecyclerView rvCodeList;
    CountryCodeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        final FragmentManager fragmentManager = getFragmentManager();

        etFilterText = view.findViewById(R.id.et_filter_code_fragment);
        etFilterText.addTextChangedListener(getTextWatcher());

        CodeFactory.getCountryCodeDAO().getFilterListCodes(getContext(), etFilterText.getText().toString());

        rvCodeList = view.findViewById(R.id.rv_phone_code_fragment);
        adapter = new CountryCodeAdapter(new ArrayList<CountryCode>(), getFragmentManager(), getContext());
        rvCodeList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCodeList.setAdapter(adapter);

        adapter.dataUpdate(CodeFactory.getCountryCodeDAO().getCodesArrayFiltered());
        return view;
    }

    public TextWatcher getTextWatcher() {

        TextWatcher textWatcher = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                CodeFactory.getCountryCodeDAO().getCodesArrayFiltered().
                        removeAll(CodeFactory.getCountryCodeDAO().
                                getCodesArrayFiltered());
            }

            public void afterTextChanged(Editable s) {
//                if (s.length() == 0) {
//                    CodeFactory.getCountryCodeDAO().getFilterListCodes(getContext(), s.toString());
//                    adapter.dataUpdate(CodeFactory.getCountryCodeDAO().getCodesArrayFiltered());
//                } else {
                    CodeFactory.getCountryCodeDAO().getFilterListCodes(getContext(), s.toString());
                    adapter.dataUpdate(CodeFactory.getCountryCodeDAO().getCodesArrayFiltered());
            }
        };
        return textWatcher;
    }
}
