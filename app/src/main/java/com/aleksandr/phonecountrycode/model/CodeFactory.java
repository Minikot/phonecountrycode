package com.aleksandr.phonecountrycode.model;

public class CodeFactory {

    public static CountryCodeDAO getCountryCodeDAO() {
        return CountryCodeBL.getInstance();
    }
}
