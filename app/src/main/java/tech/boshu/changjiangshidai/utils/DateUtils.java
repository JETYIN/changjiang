package tech.boshu.changjiangshidai.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/1/25.
 */
public class DateUtils {
    private static final String NORMAL_FORMATER = "yyyy-MM-dd HH:mm:ss";

    private static final String SERVER_FORMATER = "yyyy-MM-dd";

    public static String DataNormalFormat(String dateString) {
        SimpleDateFormat format = new SimpleDateFormat(NORMAL_FORMATER, Locale.getDefault());
        Date date = new Date(Long.valueOf(dateString));
        return format.format(date);
    }

    public static String getServerDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(SERVER_FORMATER, Locale.getDefault());
        return format.format(date);
    }
}
