package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aleksandr.phonecountrycode.model.CountryCode;

public class MainFragment extends Fragment {

    private ImageView ivCountryFlag;
    private TextView tvCodePhone;
    private Button btnEnterApp;
    private LinearLayout llCountrySelect;
    private CountryCode selectedCountry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ivCountryFlag = view.findViewById(R.id.iv_country_flag_fragment);
        tvCodePhone = view.findViewById(R.id.tv_code_phone_fragment);
        ivCountryFlag.setImageResource(R.drawable.ic_us);
        btnEnterApp = view.findViewById(R.id.btn_df_select_country);
        llCountrySelect = view.findViewById(R.id.ll_country_selection);

        llCountrySelect.setOnClickListener(v -> {
            DialogFragment dlgFragment = new CountryCodeDialogFragment();
            dlgFragment.show(getChildFragmentManager(), "dialog");
            ((CountryCodeDialogFragment) dlgFragment).setSelectedCountry(selectedCountry);
        });
        return view;
    }

    public void setCode(CountryCode countryCode) {
        tvCodePhone.setText(countryCode.getCodePlus());
        ivCountryFlag.setImageResource(countryCode.getResId());
        selectedCountry = countryCode;
    }
}
