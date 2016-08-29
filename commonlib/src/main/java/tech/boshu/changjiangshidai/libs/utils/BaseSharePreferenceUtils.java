package tech.boshu.changjiangshidai.libs.utils;

import android.content.Context;
import android.content.SharedPreferences;

import tech.boshu.changjiangshidai.libs.manager.Contextor;

/**
 * SP 基础工具类
 * Created by allipper on 2016-01-01.
 */
public class BaseSharePreferenceUtils {

    private static final String SP_NAME = "changjiangjihua";

    /**
     * 返回一个SharedPrefrence编辑对象
     *
     * @param mode
     * @return
     */
    public static SharedPreferences.Editor getEditor(int mode) {
        SharedPreferences sharedPreferences = Contextor.getInstance().getContext()
                .getSharedPreferences(SP_NAME, mode);
        return sharedPreferences.edit();
    }

    /**
     * 返回一个私有的编辑对象
     *
     * @return
     */
    public static SharedPreferences.Editor getEditor() {
        return getEditor(Context.MODE_PRIVATE);
    }

    /**
     * 返回一个私有的SharedPreference对象
     *
     * @return
     */
    public static SharedPreferences getSharedPreferences() {
        SharedPreferences sharedPreferences = Contextor.getInstance().getContext()
                .getSharedPreferences(SP_NAME, Context
                        .MODE_PRIVATE);
        return sharedPreferences;
    }

    /**
     * 返回String对象
     *
     * @param key
     * @return
     */
    public static String getString(String key) {
        return getSharedPreferences().getString(key, "");
    }

    /**
     * 返回String对象，如不存在，则返回默认值
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static String getString(String key, String defaultValue) {
        return getSharedPreferences().getString(key, defaultValue);
    }

    /**
     * 写入sharedPreferences
     *
     * @param key
     * @param value
     */
    public static void putString(String key, String value) {
        getEditor().putString(key, value).commit();
    }

    /**
     * 返回Boolean
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static boolean getBoolean(String key, boolean defaultValue) {
        return getSharedPreferences().getBoolean(key, defaultValue);
    }

    /**
     * 写入一个boolean
     *
     * @param key
     * @param value
     */
    public static void putBoolean(String key, boolean value) {
        getEditor().putBoolean(key, value).commit();
    }

    /**
     * 返回Integer
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static int getInt(String key, int defaultValue) {
        return getSharedPreferences().getInt(key, defaultValue);
    }

    /**
     * 写入一个Integer
     *
     * @param key
     * @param value
     */
    public static void putInt(String key, int value) {
        getEditor().putInt(key, value).commit();
    }

    /**
     * 返回long
     *
     * @param key
     * @param defaultValue
     * @return
     */
    public static long getLong(String key, long defaultValue) {
        return getSharedPreferences().getLong(key, defaultValue);
    }

    /**
     * 写入一个long
     *
     * @param key
     * @param value
     */
    public static void putLong(String key, long value) {
        getEditor().putLong(key, value).commit();
    }

    /**
     * 清除SharedPre中的数据
     */
    public static void clearSharedPre() {
        getEditor().clear().commit();
    }

    /**
     * 清除对应key的值
     *
     * @param key
     */
    public static void removeSharedKey(String key) {
        getEditor().remove(key).commit();
    }
}
