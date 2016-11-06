package com.example.sphien2011.fastscrollrecycler;

/**
 * Created by sphien2011 on 06/11/2016.
 */
public class Utils {
    public static String getFirstChar(String input) {
        return input.length() > 0 ? input.trim().toUpperCase().substring(0, 1) : null;
    }
}
