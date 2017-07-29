package com.codebear.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * description: 时间日期相关的辅助类
 * <p>
 * Created by CodeBear on 17/6/5.
 */

public class TimeUtil {

    /**
     * 格式化时间
     *
     * @param timeMillis 时间戳
     * @param pattern 正则(比如：yyyy-MM-dd HH:mm:ss)
     * @return
     */
    public static String timeFormat(long timeMillis, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern, Locale.CHINA);
        return format.format(new Date(timeMillis));
    }
}
