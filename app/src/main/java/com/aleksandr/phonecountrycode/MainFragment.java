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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.aleksandr.phonecountrycode.model.CountryCode;

public class MainFragment extends Fragment {

    private ImageView ivCountryFlag;
    private EditText etCodePhone;
    private TextView tvCodePhone;
    private ImageButton ibCountrySelection;
    private Button btnEnterApp;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ivCountryFlag = view.findViewById(R.id.iv_country_flag_fragment);
        etCodePhone = view.findViewById(R.id.et_code_phone_fragment);
        tvCodePhone = view.findViewById(R.id.tv_code_phone_fragment);
        ivCountryFlag.setImageResource(R.drawable.ic_flag_black_24dp);
        ibCountrySelection = view.findViewById(R.id.ib_country_selection);
        btnEnterApp = view.findViewById(R.id.btn_df_select_country);

        tvCodePhone.addTextChangedListener(getTextWatcher());

        ibCountrySelection.setOnClickListener(v -> {
            DialogFragment dlgFragment = new CountryCodeDialogFragment();
            dlgFragment.show(getChildFragmentManager(), "dialog");
        });
        return view;
    }

    public void setCode(CountryCode countryCode) {
        tvCodePhone.setText(countryCode.getCodePlus());
        ivCountryFlag.setImageResource(countryCode.getResId());
    }

    public TextWatcher getTextWatcher() {

        TextWatcher textWatcher = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            public void afterTextChanged(Editable s) {
                if (tvCodePhone.length() == 0) {
                    ivCountryFlag.setImageResource(R.drawable.ic_flag_black_24dp);
                }
            }
        };
        return textWatcher;
    }
}
