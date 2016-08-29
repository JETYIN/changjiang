package tech.boshu.changjiangshidai.utils;

import android.text.TextUtils;

import java.lang.reflect.Field;
import java.util.HashMap;

import tech.boshu.changjiangshidai.libs.utils.Utils;

/**
 * Created by allipper on 16/1/22.
 */
public class SubUtils extends Utils {

    public static <T> HashMap<String, String> toParam(T t) {
        HashMap<String, String> params = new HashMap<>();
        Class tClass = t.getClass();
        Field[] fields = tClass.getFields();
        for (Field field : fields) {
            try {
                if (field.get(t) != null && !TextUtils.isEmpty(field.get(t).toString())) {
                    params.put(field.getName(), field.get(t).toString());
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    /**
     * 根据string 类型 获取简易long 来进行比较
     * @param str
     * @param pattern
     * @return
     */
    public static long getTimeSeconds(String str,String pattern) {
        if(TextUtils.isEmpty(str)){
           return 0;
        }
        if(TextUtils.isEmpty(pattern)){
            pattern = "[-\\s:]";
        }
        return Long.valueOf(str.replaceAll(pattern, ""));
    }
}
