package com.aleksandr.phonecountrycode.model;

import android.support.annotation.DrawableRes;

public class CountryCode {

    private String name;
    private int code;
    private int digits;
    private String iso;
    private @DrawableRes int image;
    public int resId;

    public CountryCode(String name, int code, int digits, String iso) {
        this.name = name;
        this.code = code;
        this.digits = digits;
        this.iso = iso;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getDigits() {
        return digits;
    }

    public void setDigits(int digits) {
        this.digits = digits;
    }

    public String getIso() {
        return iso;
    }

    public void setIso(String iso) {
        this.iso = iso;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
