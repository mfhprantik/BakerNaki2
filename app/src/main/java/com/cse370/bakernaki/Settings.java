package com.cse370.bakernaki;

import android.content.Context;

import java.io.File;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 11/23/2017.
 */

public class Settings {
    public static String loginCredentials;

    public Settings(Context c) {
        loginCredentials = c.getDir("files", MODE_PRIVATE).getAbsolutePath() + File.separator + "login.bn";
    }

    public static String[] split(String s, char c) {
        ArrayList<String> d = new ArrayList<>();

        String t = "";
        for(int i = 0 ; i < s.length() ; i++) {
            char k = s.charAt(i);

            if (k == c) {
                d.add(t);
                t = "";
            }
            else t += k;
        }
        if (t.length() > 0) d.add(t);

        return d.toArray(new String[d.size()]);
    }
}