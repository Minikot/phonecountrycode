package com.aleksandr.phonecountrycode;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.aleksandr.phonecountrycode.model.CountryCode;
import com.aleksandr.phonecountrycode.model.CountryCodeRepository;

import java.util.ArrayList;

public class CountryCodeDialogFragment extends DialogFragment {

    private EditText etFilterCode;
    private ImageButton ibBack;
    private RecyclerView rvCodeList;
    private CountryCodeAdapter adapter;
    private CodeSelectedListener onCodeSelectedListener;
    private CountryCodeView countryCodeViewInvisible;
    private CountryCode selectedCountry;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * Строка нужна для запуска DialogFragment на весь экран. В styles добавить стиль <style name="YourCustomeThemeName" ...
         */
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.YourCustomeThemeName);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment_country_code, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ibBack = view.findViewById(R.id.ib_back_to_main_fragment);
        etFilterCode = view.findViewById(R.id.et_filter_code_dialog_fragment);
        etFilterCode.addTextChangedListener(getTextWatcher());
        countryCodeViewInvisible = view.findViewById(R.id.country_view_invisible);

        if (selectedCountry != null) {
            countryCodeViewInvisible.setVisibility(View.VISIBLE);
            countryCodeViewInvisible.setView(selectedCountry);
            countryCodeViewInvisible.rbChecked.setChecked(true);
        }

        countryCodeViewInvisible.setOnClickListener(v -> dismiss());

        ibBack.setOnClickListener(v -> dismiss());

        rvCodeList = view.findViewById(R.id.rv_phone_code_dialog_fragment);
        adapter = new CountryCodeAdapter(new ArrayList<CountryCode>(), new CountryCodeAdapter.CountryItemListener() {
            @Override
            public void countrySelect(CountryCode countryCode) {
                onCodeSelectedListener.onChangeCode(countryCode);
                etFilterCode.setText("");
                dismiss();
            }
        });

        rvCodeList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCodeList.setAdapter(adapter);

        adapter.dataUpdate(CountryCodeRepository.filterListCodesArray(etFilterCode.getText().toString(), getContext()));
    }

    public interface CodeSelectedListener {
        void onChangeCode(CountryCode countryCode);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CodeSelectedListener) {
            onCodeSelectedListener = (CodeSelectedListener) context;
        } else {
            System.out.println("fragment from BaseAuthFragment attached not to AuthNavigator");     //TODO Change text
        }
    }

    public TextWatcher getTextWatcher() {

        return new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                CountryCodeRepository.filterListCodesArray(s.toString(), getContext());
                adapter.dataUpdate(CountryCodeRepository.filterListCodesArray(etFilterCode.getText().toString(), getContext()));
            }
        };
    }

    public void setSelectedCountry(CountryCode countryCode) {
        selectedCountry = countryCode;
    }
}
