package com.aleksandr.phonecountrycode.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryCode {

    private String name;
    private int code;
    private ArrayList<Integer> digits;
    @SerializedName("iso_3166_1")
    private String iso;
    public int resId;

    public CountryCode(String name, int code, ArrayList<Integer> digits, String iso) {
        this.name = name;
        this.code = code;
        this.digits = digits;
        this.iso = iso;
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
}
