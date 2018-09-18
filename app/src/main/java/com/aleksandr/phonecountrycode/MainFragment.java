package com.aleksandr.phonecountrycode;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.aleksandr.phonecountrycode.model.CountryCodeBL;

public class MainFragment extends Fragment {
    public static final String CODE = "code";
    public static final String RES_ID = "resId";

    ImageView ivCountryFlag;
    EditText etCodePhone;
    Button btnListCountry;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        ivCountryFlag = view.findViewById(R.id.iv_country_flag_fragment);
        etCodePhone = view.findViewById(R.id.et_code_phone_fragment);

        btnListCountry = view.findViewById(R.id.btn_list_country);
        btnListCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getFragmentManager();
                SecondFragment secondFragment;
                FragmentTransaction ft = fragmentManager.beginTransaction();
                secondFragment = new SecondFragment();

                ft.replace(R.id.container, secondFragment, "secondFragment");
                ft.addToBackStack(null);
                ft.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                ft.commit();
                CountryCodeBL.getInstance().loadGradle(getContext());
            }
        });

        Bundle args = getArguments();
        if (args == null) {
            ivCountryFlag.setImageResource(R.drawable.ic_flag_black_24dp);

        } else {
            etCodePhone.setText(args.getString("code"));
            ivCountryFlag.setImageResource(args.getInt(RES_ID));
        }
        return view;
    }
}
