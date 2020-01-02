package com.cse370.bakernaki;

import java.util.HashMap;

/**
 * Created by Administrator on 11/23/2017.
 */

public class Employer {

    public static String TYPE = "2";
    public static String EMAIL;
    public static String PASSWORD;

    public static String FULLNAME;
    public static String ADDRESS;
    public static String PHONE;
    public static String DETAILS;

    public static String NATURE;
    public static String LOCATION;
    public static String SALARY;
    public static String DESCRIPTION;
    public static String DEADLINE;

    public static String CATEGORIES;

    public static String DEGREE;
    public static String EXPERIENCE;

    public static HashMap<String, String> getData() {
        HashMap<String, String> data = new HashMap<>();

        data.put("type", TYPE);
        data.put("email", EMAIL);
        data.put("password", PASSWORD);
        data.put("name", FULLNAME);
        data.put("address", ADDRESS);
        data.put("phone", PHONE);
        data.put("details", DETAILS);
        data.put("nature", NATURE);
        data.put("location", LOCATION);
        data.put("salary", SALARY);
        data.put("description", DESCRIPTION);
        data.put("deadline", DEADLINE);
        data.put("degree", DEGREE);
        data.put("categories", CATEGORIES);
        data.put("experience", EXPERIENCE);

        return data;
    }

    public static HashMap<String, String> getCircularData() {
        HashMap<String, String> data = new HashMap<>();

        data.put("id", User.id + "");
        data.put("nature", NATURE);
        data.put("location", LOCATION);
        data.put("salary", SALARY);
        data.put("description", DESCRIPTION);
        data.put("deadline", DEADLINE);
        data.put("degree", DEGREE);
        data.put("categories", CATEGORIES);
        data.put("experience", EXPERIENCE);

        return data;
    }
}
