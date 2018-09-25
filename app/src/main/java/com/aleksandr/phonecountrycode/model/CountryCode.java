package com.aleksandr.phonecountrycode.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryCode {
    private String name;
    private int code;
    private ArrayList<Integer> digits;
    @SerializedName("iso_3166_1")
    private String iso;
    private int resId;

    public CountryCode(String name, int code, ArrayList<Integer> digits, String iso, int resId) {
        this.name = name;
        this.code = code;
        this.digits = digits;
        this.iso = iso;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }

    public int getCode() {
        return code;
    }

    public String getIso() {
        return iso;
    }

    public ArrayList<Integer> getDigits() {
        return digits;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
