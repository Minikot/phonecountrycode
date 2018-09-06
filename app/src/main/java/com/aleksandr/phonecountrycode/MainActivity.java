package com.aleksandr.phonecountrycode;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnListCountry;
    TextView tvTest;
    TextView tvRaw;
//    private ArrayList<CountryCode> countryCodesFin = CodeFactory.getCountryCodeDAO().getCountryCodesArray();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTest = findViewById(R.id.tv_test);
        tvRaw = findViewById(R.id.tv_raw);
        btnListCountry = findViewById(R.id.btn_list_country);
        btnListCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);

//                tvRaw.setText(CodeFactory.getCountryCodeDAO().loadGradle2(getApplicationContext()));
            }
        });
    }
}
