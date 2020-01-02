package com.cse370.bakernaki;

import java.util.HashMap;

/**
 * Created by Administrator on 11/23/2017.
 */

public class Employee {
    public static String TYPE = "1";
    public static String EMAIL;
    public static String PASSWORD;

    public static String FULLNAME;
    public static String SEX;
    public static String ADDRESS;
    public static String PHONE;
    public static String BIRTHDAY;

    public static String CATEGORIES;

    public static String DEGREE;
    public static String YEAR;
    public static String INSTITUTION;

    public static String EXPERIENCE;
    public static String COMPANIES;
    public static String WORK;

    public static String INTERPERSONAL;
    public static String COMPUTER;
    public static String LANGUAGE;
    public static String ACHIEVEMENTS;

    public static HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();

        data.put("type", TYPE);
        data.put("email", EMAIL);
        data.put("password", PASSWORD);
        data.put("name", FULLNAME);
        data.put("sex", SEX);
        data.put("address", ADDRESS);
        data.put("phone", PHONE);
        data.put("birthday", BIRTHDAY);
        data.put("degree", DEGREE);
        data.put("year", YEAR);
        data.put("institution", INSTITUTION);
        data.put("categories", CATEGORIES);
        data.put("experience", EXPERIENCE);
        data.put("companies", COMPANIES);
        data.put("work", WORK);
        data.put("interpersonal", INTERPERSONAL);
        data.put("computer", COMPUTER);
        data.put("language", LANGUAGE);
        data.put("achievements", ACHIEVEMENTS);

        return data;
    }
}
