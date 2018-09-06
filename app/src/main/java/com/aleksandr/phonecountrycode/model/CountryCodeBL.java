package com.aleksandr.phonecountrycode.model;

import android.content.Context;
import android.content.res.Resources;
import android.text.Editable;
import android.text.TextWatcher;

import com.aleksandr.phonecountrycode.CountryCodeAdapter;
import com.aleksandr.phonecountrycode.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CountryCodeBL implements CountryCodeDAO {

    private static CountryCodeBL instance = null;

    public ArrayList<CountryCode> codesArrayFiltered = new ArrayList<>();

    public ArrayList<CountryCode> getCodesArrayFiltered() {
        return codesArrayFiltered;
    }

    public void getFilterListCodes(Context appContext, String str) {
        filterListCodes(loadGradle2(appContext), str);
    }

    public static CountryCodeBL getInstance() {
        if (instance == null) {
            instance = new CountryCodeBL();
        }
        return instance;
    }

    /**
     * RAW Start
     */
    public String loadGradle2(Context appContext) {
        Resources res = appContext.getResources();
        InputStream is = res.openRawResource(R.raw.e164_country_codes);
        Scanner scanner = new Scanner(is);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append((scanner.nextLine()));
        }
        return builder.toString();
    }

    //Тестовый метод со студентами
    private void parseJson(String json) {
        StringBuilder builder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject studentGradle = root.getJSONObject("student-grades");

//            builder.append("Name: ")
//                    .append(studentGradle.getString("name"))
//                    .append("\n");
//
//            builder.append("Full Time: ")
//                    .append(studentGradle.getBoolean("full-time"))
//                    .append("\n\n");

            JSONArray courses = studentGradle.getJSONArray("courses");

            for (int i = 0; i < courses.length(); i++) {
                JSONObject course = courses.optJSONObject(i);
                builder.append(course.getString("name"))
                        .append(": ")
                        .append(course.getString("grade"))
                        .append("\n");
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
//        tvRaw.setText(builder.toString());
    }

    private String parseJsonCountryCode(String json) {
        StringBuilder builder = new StringBuilder();
        try {
            JSONObject root = new JSONObject(json);
            JSONObject countryCodes = root.getJSONObject("code");
            JSONArray lists = countryCodes.getJSONArray("list");

            for (int i = 0; i < lists.length(); i++) {
                JSONObject list = lists.optJSONObject(i);
                builder.append(list.getString("name"))
                        .append(": ")
                        .append(list.getInt("code"))
                        .append(": ")
                        .append(list.getString("digits"))
                        .append(": ")
                        .append(list.getString("iso_3166_1"))
                        .append("\n");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

//    private void parseJsonCountryCodeStringToArray(String json) {
//        try {
//            JSONObject root = new JSONObject(json);
//            JSONObject countryCodes = root.getJSONObject("code");
//            JSONArray lists = countryCodes.getJSONArray("list");
//
//            for (int i = 0; i < lists.length(); i++) {
//                countryCodesArray.add(new CountryCode(
//                                lists.optJSONObject(i).getString("name"),
//                                lists.optJSONObject(i).getInt("code"),
////                                lists.optJSONObject(i).getInt("digits"),
//                                0,
//                                lists.optJSONObject(i).getString("iso_3166_1"))
//                );
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }
    /**
     * RAW End
     */

    public void filterListCodes(String json, String st) {

        try {
            JSONObject root = new JSONObject(json);
            JSONObject countryCodes = root.getJSONObject("code");
            JSONArray lists = countryCodes.getJSONArray("list");

            boolean isContain = true;

            if (st == null) {
                for (int i = 0; i < lists.length(); i++) {
                    codesArrayFiltered.add(new CountryCode(
                            lists.optJSONObject(i).getString("name"),
                            lists.optJSONObject(i).getInt("code"),
                            0,
                            lists.optJSONObject(i).getString("iso_3166_1")));
                }

            } else if (st != null) {
                for (int i = 0; i < lists.length(); i++) {
                    isContain = lists.optJSONObject(i).getString("name").toLowerCase().contains(st);

                    if (isContain) {
                        codesArrayFiltered.add(new CountryCode(
                                lists.optJSONObject(i).getString("name"),
                                lists.optJSONObject(i).getInt("code"),
                                0,
                                lists.optJSONObject(i).getString("iso_3166_1")));
                    } else {

                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * TextWatcher
     */
    public TextWatcher getTextWatcher(final CountryCodeAdapter adapter, final Context appContext, final String str) {

        TextWatcher textWatcher = new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                codesArrayFiltered.removeAll(codesArrayFiltered);
                adapter.dataUpdate(getCodesArrayFiltered());
            }

            public void afterTextChanged(Editable s) {
                if (s.length() == 0) {
                    System.out.println("ZERO");
                    getFilterListCodes(appContext, str);
                    adapter.dataUpdate(codesArrayFiltered);

                } else {
                    System.out.println("FUUUUUUUCK");
                    getFilterListCodes(appContext, str);
                    adapter.dataUpdate(codesArrayFiltered);
//                    System.out.println("Size list - " + codesArrayFiltered.size());
                }
            }
        };
        return textWatcher;
    }

    /** -2- start
     *  Рабочий вариант с парсингом стринги, созданием списка. Но внутрннний массив не реализован.
     */
//
//    public static String response = "{\"response\": [{\"name\":\"SAT, GMSS\", \"code\":881, \"digits\":[9], \"iso_3166_1\":\"00\"}, " +
//            "{\"name\":\"SAT, Inmarsat\", \"code\":870, \"digits\":[9, 10], \"iso_3166_1\":\"00\"}, " +
//            "{\"name\":\"Andorra\", \"code\":376, \"digits\":[6, 8, 9], \"iso_3166_1\":\"ad\"}, " +
//            "{\"name\":\"Andorra\", \"code\":376, \"digits\":[6, 8, 9], \"iso_3166_1\":\"ad\"}, " +
//            "{\"name\":\"United Arab Emirates\", \"code\":971, \"digits\":[8, 9], \"iso_3166_1\":\"ae\"}]}";
//
//    public String readJsonFromFile() {
//        StringBuilder text = new StringBuilder();
//
//        try {
//            BufferedReader reader = new BufferedReader(new InputStreamReader(
//                    getApplicationContext().getAssets()
//                            .open("/Users/Aleksandr/Desktop/e164_country_codes.json")));
//            String line;
//
//            while ((line = reader.readLine()) != null) {
//                text.append(line).append("\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return text.toString();
//    }
//
//
//    protected void onPostExecute(String response) {
//
//        ArrayList<CountryCode> countryCodesArray = new ArrayList<>();
//
//        try {
//            JSONObject jsonResponse = new JSONObject(response);
//            JSONArray jsonArray = jsonResponse.getJSONArray("response");
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject countryInfo = jsonArray.getJSONObject(i);
//
//                countryCodesArray.add(new CountryCode(countryInfo.getString("name"),
//                        countryInfo.getInt("code"),
//                        countryInfo.getJSONArray("digits").getInt(0),
//                        countryInfo.getString("iso_3166_1")));
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        String result = "Dig 1: " + countryCodesArray.size();
//    }
//
    /** -2- end
     *
     */

    /**
     * Не помню что за код
     */
//    static List<CountryCode> importFrom(Context context){
//        Gson gson = new Gson();
//        DataItem dataItem = gson.fromJson(response, DataItem.class);
//        return dataItem.getCountryCodes();
//    }
//
//    private static class DataItem {
//        private List<CountryCode> countryCodes;
//
//        public List<CountryCode> getCountryCodes() {
//            return countryCodes;
//        }
//
//        public void setCountryCodes(List<CountryCode> countryCodes) {
//            this.countryCodes = countryCodes;
//        }
//    }
    /**
     */
}
