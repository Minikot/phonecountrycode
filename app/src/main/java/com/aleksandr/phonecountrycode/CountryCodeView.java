package com.aleksandr.phonecountrycode;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.aleksandr.phonecountrycode.model.CountryCode;

public class CountryCodeView extends LinearLayout {

    public ImageView ivFlagView;
    public TextView tvCodeView;
    public TextView tvCountryView;
    public RadioButton rbChecked;

    public CountryCodeView(Context context) {
        this(context, null);
    }

    public CountryCodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CountryCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public CountryCodeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        LayoutInflater.from(context).inflate(
                R.layout.view_country_code, this);

        ivFlagView = findViewById(R.id.iv_flag_view);
        tvCodeView = findViewById(R.id.tv_code_view);
        tvCountryView = findViewById(R.id.tv_country_view);
        rbChecked = findViewById(R.id.rb_checked);
    }

    public void setView(CountryCode countryCode) {
        ivFlagView.setImageResource(countryCode.getResId());
        tvCountryView.setText(countryCode.getName());
        tvCodeView.setText(String.valueOf(countryCode.getCodePlus()));
    }
}
