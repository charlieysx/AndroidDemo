package com.codebear.demo.utils;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

/**
 * description:
 * <p>
 * Created by CodeBear on 17/6/6.
 */

public class T {
    public static Gson gson = new Gson();

    public static void l(Context context, Object o) {
        Toast.makeText(context, getString(o), Toast.LENGTH_LONG).show();
    }

    public static void s(Context context, Object o) {
        Toast.makeText(context, getString(o), Toast.LENGTH_SHORT).show();
    }

    private static String getString(Object o) {
        StringBuilder result = new StringBuilder(new Gson().toJson(o));
        int index = result.indexOf("\"");
        if(index != -1) {
            result.deleteCharAt(index);
        }
        int lastIndex = result.lastIndexOf("\"");
        if(lastIndex != -1) {
            result.deleteCharAt(lastIndex);
        }
        return result.toString();
    }
}
