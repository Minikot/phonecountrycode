package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.aleksandr.phonecountrycode.model.CodeFactory;
import com.aleksandr.phonecountrycode.model.CountryCode;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private EditText etFilterText;
    RecyclerView rvCodeList;
    private CountryCodeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        etFilterText = findViewById(R.id.et_filter_code);
        etFilterText.addTextChangedListener(getTextWatcher());

        CodeFactory.getCountryCodeDAO().getFilterListCodes(getApplicationContext(), etFilterText.getText().toString());

        rvCodeList = findViewById(R.id.rv_phone_code);
        adapter = new CountryCodeAdapter(new ArrayList<CountryCode>());
        rvCodeList.setLayoutManager(new LinearLayoutManager(getBaseContext()));
        rvCodeList.setAdapter(adapter);

        adapter.dataUpdate(CodeFactory.getCountryCodeDAO().getCodesArrayFiltered());
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
                if (s.length() == 0) {
                    System.out.println("ZERO");
                    CodeFactory.getCountryCodeDAO().getFilterListCodes(getApplicationContext(), s.toString());
                    adapter.dataUpdate(CodeFactory.getCountryCodeDAO().getCodesArrayFiltered());

                } else {
                    System.out.println("FUUUUUUUCK");

                    CodeFactory.getCountryCodeDAO().getFilterListCodes(getApplicationContext(), s.toString());
//                    System.out.println("Size list - " + CodeFactory.getCountryCodeDAO().
//                            getCodesArrayFiltered(getApplicationContext(), etFilterText.getText().toString()).size());
                    adapter.dataUpdate(CodeFactory.getCountryCodeDAO().getCodesArrayFiltered());
                }
            }
        };
        return textWatcher;
    }
}
