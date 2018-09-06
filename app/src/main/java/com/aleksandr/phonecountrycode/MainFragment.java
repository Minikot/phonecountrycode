package com.aleksandr.phonecountrycode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainFragment extends Fragment {

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
                Intent intent = new Intent(MainFragment.this, )
                startActivity(intent);
            }
        });
        return view;
    }
}
