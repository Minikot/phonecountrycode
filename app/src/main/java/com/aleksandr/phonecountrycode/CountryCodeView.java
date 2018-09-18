package com.aleksandr.phonecountrycode;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CountryCodeView extends LinearLayout {

    /**
     *  Я ПЕРЕДЕЛАЮ ПОТОМ ПОЛНОСТЬЮ. ПРОСТО Я ДУМАЛ, ЧТО НУЖНО ЗАМЕНИТЬ ПОЛНОСТЬЮ ВСЕ ЭЛЕМЕНТЫ АЙТЕМА. НЕ ОБРАЩАЙ ВНИМАНИЕ НА ЭТОТ ВЬЮ
     */
    private ImageView ivFlagView;
    private TextView tvCodeView;
    private TextView tvCountryView;

    public CountryCodeView(Context context) {
        this(context, null);
    }

    public CountryCodeView(Context context, @Nullable AttributeSet attrs) {
        this(context, null, 0);
    }

    public CountryCodeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, 0, 0);
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
    }

}
