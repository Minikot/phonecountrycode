package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aleksandr.phonecountrycode.model.CountryCode;

public class MainFragment extends Fragment {

    ImageView ivCountryFlag;
    EditText etCodePhone;
    Button btnDialogFragment;

    DialogFragment dlgFragment;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ivCountryFlag = view.findViewById(R.id.iv_country_flag_fragment);
        etCodePhone = view.findViewById(R.id.et_code_phone_fragment);
        etCodePhone.addTextChangedListener(getTextWatcher());
        dlgFragment = new CountryCodeDialogFragment();

        ivCountryFlag.setImageResource(R.drawable.ic_flag_black_24dp);

        btnDialogFragment = view.findViewById(R.id.btn_dialog_fragment);

        btnDialogFragment.setOnClickListener(v -> {
            CountryCodeDialogFragment countryCodeDialogFragment = new CountryCodeDialogFragment();
            countryCodeDialogFragment.show(getChildFragmentManager(), "dialog");
        });
        return view;
    }

    public void setCode(CountryCode countryCode) {
        etCodePhone.setText("+ " + String.valueOf(countryCode.getCode()));
        ivCountryFlag.setImageResource(
                countryCode.resId = getContext().getResources()
                        .getIdentifier("ic_" + countryCode.getIso(), "drawable", getContext().getPackageName()));
    }

    public TextWatcher getTextWatcher() {

        TextWatcher textWatcher = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (etCodePhone.length() == 0) {
                    ivCountryFlag.setImageResource(R.drawable.ic_flag_black_24dp);
                }
            }
        };
        return textWatcher;
    }
}
