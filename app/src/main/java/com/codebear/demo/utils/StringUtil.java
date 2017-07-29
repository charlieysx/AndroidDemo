package com.codebear.demo.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import com.google.gson.Gson;

/**
 * description:字符串相关的辅助类
 * <p>
 * Created by CodeBear on 17/6/5.
 */

public class StringUtil {
    /**
     * 检测String是否全是中文
     *
     * @param name
     * @return
     */
    public static boolean checkChinese(String name) {
        boolean result = true;
        char[] cTemp = name.toCharArray();
        for (int i = 0; i < name.length(); ++i) {
            if (!isChinese(cTemp[i])) {
                result = false;
                break;
            }
        }
        return result;
    }

    /**
     * 判定输入汉字
     *
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 复制到剪切版
     *
     * @param context
     * @param text
     */
    public static void copyToSystem(Context context, CharSequence text) {
        ClipboardManager cm = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData myClip;
        myClip = ClipData.newPlainText("text", text);
        cm.setPrimaryClip(myClip);
    }

    /**
     * 判断字符串是否为空
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        return null == s || s.length() == 0 || s.trim().length() == 0;
    }

    public static String objectToString(Object o) {
        return new Gson().toJson(o);
    }
}
