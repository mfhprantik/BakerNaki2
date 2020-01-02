package com.cse370.bakernaki;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Api {

    private static final String ROOT_URL = "http://chakri123.000webhostapp.com/android/v1/Api.php?apicall=";

    public static final String URL_LOGIN = ROOT_URL + "login";
    public static final String URL_USEREXIST = ROOT_URL + "userExist";
    public static final String URL_GETCATEGORIES = ROOT_URL + "getCategories";
    public static final String URL_GETROLES = ROOT_URL + "getSubCategories";
    public static final String URL_GETLEVELS = ROOT_URL + "getLevels";
    public static final String URL_GETDEGREES = ROOT_URL + "getDegrees";
    public static final String URL_REGISTEREMPLOYEE = ROOT_URL + "registerEmployee";
    public static final String URL_REGISTEREMPLOYER = ROOT_URL + "registerEmployer";
    public static final String URL_GETMATCHEDCIRCULARS = ROOT_URL + "getMatchedCirculars";
    public static final String URL_GETCIRCULARDATA = ROOT_URL + "getCircularData";
    public static final String URL_GETEMPLOYERDATA = ROOT_URL + "getEmployerData";
    public static final String URL_GETCATEGORYNAME = ROOT_URL + "getCategoryName";
    public static final String URL_GETDEGREENAME = ROOT_URL + "getDegreeName";
    public static final String URL_GETEMPLOYEEPROFILE = ROOT_URL + "getEmployeeProfile";
    public static final String URL_GETJOBPREFERENCE = ROOT_URL + "getJobPreference";
    public static final String URL_GETCIRCULARS = ROOT_URL + "getCirculars";
    public static final String URL_GETCANDIDATES = ROOT_URL + "getCandidates";
    public static final String URL_MATCH = ROOT_URL + "match";
    public static final String URL_SETCANDIDATES = ROOT_URL + "setCandidates";
    public static final String URL_ADDCIRCULAR = ROOT_URL + "addCircular";
    public static final String URL_DELETECIRCULAR = ROOT_URL + "deleteCircular";
    public static final String URL_ADDINTEREST = ROOT_URL + "addInterest";
    public static final String URL_REMOVEINTERST = ROOT_URL + "removeInterest";
    public static final String URL_SAVEEMPLOYEEPROFILE = ROOT_URL + "saveEmployeeProfile";
    public static final String URL_SAVEEMPLOYERPROFILE = ROOT_URL + "saveEmployerProfile";

    public static String RESULT;
    public static String PROFILEDATA;
    public static String[][] CANDIDATEDATA;
    public static String[][] CIRCULARDATA;

    public static void login() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_LOGIN, User.credentials);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static boolean userExist(final String email) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("email", email);

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_USEREXIST, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;

        if (RESULT.equals("0")) return false;
        else return true;
    }

    public static ArrayList<ArrayList<String>> getCategories(final String placeholder) {
        final ArrayList<ArrayList<String>> categories = new ArrayList<>();
        categories.add(new ArrayList<String>());
        categories.add(new ArrayList<String>());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestHandler rh = new RequestHandler();
                String[] s = Settings.split(rh.sendGetRequest(URL_GETCATEGORIES), '?');

                categories.get(0).add("0");
                categories.get(1).add(placeholder);

                for (int i = 0; i < s.length - 1; i += 2) {
                    categories.get(0).add(s[i]);
                    categories.get(1).add(s[i + 1]);
                }
            }
        });

        t.start();
        while (t.isAlive()) ;

        return categories;
    }

    public static ArrayList<ArrayList<String>> getRoles(final String placeHolder, final String id) {
        final ArrayList<ArrayList<String>> roles = new ArrayList<>();
        roles.add(new ArrayList<String>());
        roles.add(new ArrayList<String>());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("id", id);

                RequestHandler rh = new RequestHandler();
                String[] s = Settings.split(rh.sendPostRequest(URL_GETROLES, postdata), '?');

                roles.get(0).add("0");
                roles.get(1).add(placeHolder);

                for (int i = 0; i < s.length - 1; i += 2) {
                    roles.get(0).add(s[i]);
                    roles.get(1).add(s[i + 1]);
                }
            }
        });

        t.start();
        while (t.isAlive()) ;

        return roles;
    }

    public static ArrayList<ArrayList<String>> getLevels(final String placeholder) {
        final ArrayList<ArrayList<String>> levels = new ArrayList<>();
        levels.add(new ArrayList<String>());
        levels.add(new ArrayList<String>());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestHandler rh = new RequestHandler();
                String[] s = Settings.split(rh.sendGetRequest(URL_GETLEVELS), '?');

                levels.get(0).add("0");
                levels.get(1).add(placeholder);

                for (int i = 0; i < s.length - 1; i += 2) {
                    levels.get(0).add(s[i]);
                    levels.get(1).add(s[i + 1]);
                }
            }
        });

        t.start();
        while (t.isAlive()) ;

        return levels;
    }

    public static ArrayList<ArrayList<String>> getDegrees(final String placeHolder, final String id) {
        final ArrayList<ArrayList<String>> degrees = new ArrayList<>();
        degrees.add(new ArrayList<String>());
        degrees.add(new ArrayList<String>());

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("id", id);

                RequestHandler rh = new RequestHandler();
                String[] s = Settings.split(rh.sendPostRequest(URL_GETDEGREES, postdata), '?');

                degrees.get(0).add("0");
                degrees.get(1).add(placeHolder);

                for (int i = 0; i < s.length - 1; i += 2) {
                    degrees.get(0).add(s[i]);
                    degrees.get(1).add(s[i + 1]);
                }
            }
        });

        t.start();
        while (t.isAlive()) ;

        return degrees;
    }

    public static void registerEmployee() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = Employee.getData();

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_REGISTEREMPLOYEE, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void registerEmployer() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = Employer.getData();

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_REGISTEREMPLOYER, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void findCandidatesData(final String circular_ID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("circular_ID", circular_ID);

                RequestHandler rh = new RequestHandler();
                String[] candidates = Settings.split(getCandidates(circular_ID), '_');

                if (candidates.length <= 1) {
                    CANDIDATEDATA = new String[0][0];
                    return;
                }

                CANDIDATEDATA = new String[candidates.length - 1][17];
                for (int i = 1; i < candidates.length; i++) {
                    getEmployeeProfile(candidates[i]);
                    String k = candidates[i] + "?" + PROFILEDATA;
                    getJobPreference(candidates[i]);
                    k += RESULT;

                    CANDIDATEDATA[i - 1] = Settings.split(k, '?');
                }
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static String getCandidates(final String circular_ID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("circular_ID", circular_ID);

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_GETCANDIDATES, postdata);

            }
        });

        t.start();
        while (t.isAlive()) ;

        return RESULT;
    }

    public static void findMatches() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("id", User.id + "");

                RequestHandler rh = new RequestHandler();
                String[] circulars = Settings.split(rh.sendPostRequest(URL_GETMATCHEDCIRCULARS, postdata), '?');

                CIRCULARDATA = new String[circulars.length][10];

                for (int i = 0; i < circulars.length; i++) {
                    String[] circularData = Settings.split(getCircularData(circulars[i]), '?');

                    String[] categories = Settings.split(circularData[1], '_');
                    String[] degrees = Settings.split(circularData[2], '_');
                    String employerID = circularData[circularData.length - 1];
                    String employerData = getEmployerData(employerID);

                    String categoryData = "";
                    for (int j = 1; j < categories.length; j++) {
                        if (j == 1) categoryData += getCategoryName(categories[j]);
                        else categoryData += ", " + getCategoryName(categories[j]);
                    }

                    String degreeData = "";
                    for (int j = 1; j < degrees.length; j++) {
                        if (j == 1) degreeData += getDegreeName(degrees[j]);
                        else degreeData += ", " + getDegreeName(degrees[j]);
                    }

                    circularData[1] = categoryData;
                    circularData[2] = degreeData;
                    circularData[9] = employerData;

                    CIRCULARDATA[i] = circularData;
                }
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static String getCircularData(final String circular_ID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postData = new HashMap<>();
                postData.put("circular_ID", circular_ID);

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_GETCIRCULARDATA, postData);
            }
        });

        t.start();
        while (t.isAlive()) ;

        return RESULT;
    }

    public static String getEmployerData(final String employerID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postData = new HashMap<>();
                postData.put("employer_ID", employerID);

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_GETEMPLOYERDATA, postData);
            }
        });

        t.start();
        while (t.isAlive()) ;

        return RESULT;
    }

    public static String getCategoryName(final String categoryID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postData = new HashMap<>();
                postData.put("category_ID", categoryID);

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_GETCATEGORYNAME, postData);
            }
        });

        t.start();
        while (t.isAlive()) ;

        return RESULT;
    }

    public static String getDegreeName(final String degreeID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postData = new HashMap<>();
                postData.put("degree_ID", degreeID);

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_GETDEGREENAME, postData);
            }
        });

        t.start();
        while (t.isAlive()) ;

        return RESULT;
    }

    public static void getEmployeeProfile(final String employee_ID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("id", "" + employee_ID);

                RequestHandler rh = new RequestHandler();
                PROFILEDATA = rh.sendPostRequest(URL_GETEMPLOYEEPROFILE, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void getJobPreference(final String id) {
        RESULT = "";

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("id", "" + id);

                RequestHandler rh = new RequestHandler();
                String[] data = Settings.split(rh.sendPostRequest(URL_GETJOBPREFERENCE, postdata), '?');

                String[] categories = Settings.split(data[0], '_');
                String categoryData = "";
                for (int j = 1; j < categories.length; j++) {
                    if (j == 1) categoryData += getCategoryName(categories[j]);
                    else categoryData += ", " + getCategoryName(categories[j]);
                }
                data[0] = categoryData;

                String degreeData = getDegreeName(data[4]);
                data[4] = degreeData;

                for (int i = 0; i < data.length; i++) {
                    if (i == 0) RESULT = data[i] + "?";
                    else RESULT += data[i] + "?";
                }
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void findCirculars() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("id", User.id + "");

                RequestHandler rh = new RequestHandler();
                String[] circulars = Settings.split(rh.sendPostRequest(URL_GETCIRCULARS, postdata), '?');

                CIRCULARDATA = new String[circulars.length][10];

                for (int i = 0; i < circulars.length; i++) {
                    String[] circularData = Settings.split(getCircularData(circulars[i]), '?');

                    String[] categories = Settings.split(circularData[1], '_');
                    String[] degrees = Settings.split(circularData[2], '_');
                    String employerID = circularData[circularData.length - 1];
                    String employerData = getEmployerData(employerID);

                    String categoryData = "";
                    for (int j = 1; j < categories.length; j++) {
                        if (j == 1) categoryData += getCategoryName(categories[j]);
                        else categoryData += ", " + getCategoryName(categories[j]);
                    }

                    String degreeData = "";
                    for (int j = 1; j < degrees.length; j++) {
                        if (j == 1) degreeData += getDegreeName(degrees[j]);
                        else degreeData += ", " + getDegreeName(degrees[j]);
                    }

                    circularData[1] = categoryData;
                    circularData[2] = degreeData;
                    circularData[9] = employerData;

                    CIRCULARDATA[i] = circularData;
                }
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void match(final String circular_ID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("circular_ID", circular_ID);

                RequestHandler rh = new RequestHandler();
                String[] circular = Settings.split(rh.sendPostRequest(URL_GETCIRCULARDATA, postdata), '?');

                String[] category = Settings.split(circular[1], '_');

                String[] categories = new String[3];
                categories[0] = "#";
                categories[1] = "#";
                categories[2] = "#";

                for (int i = 1; i < category.length; i++) {
                    categories[i - 1] = category[i];
                }

                String[] degree = Settings.split(circular[2], '_');

                int min = Integer.MAX_VALUE;
                for (int i = 1; i < degree.length; i++) {
                    int d = Integer.parseInt(degree[i]);

                    if (d < min) min = d;
                }

                postdata.put("category1", categories[0]);
                postdata.put("category2", categories[1]);
                postdata.put("category3", categories[2]);
                postdata.put("degree", (min - min % 100) + "");
                postdata.put("experience", circular[3]);

                RESULT = "_" + rh.sendPostRequest(URL_MATCH, postdata);
                postdata.put("candidates", RESULT);

                rh.sendPostRequest(URL_SETCANDIDATES, postdata);
            }
        });

        t.start();
//        while (t.isAlive()) ;
    }

    public static void addCircular() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = Employer.getCircularData();

                RequestHandler rh = new RequestHandler();
                RESULT = rh.sendPostRequest(URL_ADDCIRCULAR, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void deleteCircular(final String circular_ID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("circular_ID", circular_ID);

                RequestHandler rh = new RequestHandler();
                rh.sendPostRequest(URL_DELETECIRCULAR, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void addInterest(final String circular_ID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("circular_ID", circular_ID);
                postdata.put("id", User.id + "");

                RequestHandler rh = new RequestHandler();
                rh.sendPostRequest(URL_ADDINTEREST, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void removeInterest(final String circular_ID) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                HashMap<String, String> postdata = new HashMap<>();
                postdata.put("circular_ID", circular_ID);
                postdata.put("id", User.id + "");

                RequestHandler rh = new RequestHandler();
                rh.sendPostRequest(URL_REMOVEINTERST, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void saveEmployeeProfile(final HashMap<String, String> postdata) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestHandler rh = new RequestHandler();
                rh.sendPostRequest(URL_SAVEEMPLOYEEPROFILE, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }

    public static void saveEmployerProfile(final HashMap<String, String> postdata) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                RequestHandler rh = new RequestHandler();
                rh.sendPostRequest(URL_SAVEEMPLOYERPROFILE, postdata);
            }
        });

        t.start();
        while (t.isAlive()) ;
    }
}