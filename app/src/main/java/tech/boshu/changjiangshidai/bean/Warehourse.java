package tech.boshu.changjiangshidai.bean;

import com.google.gson.Gson;

/**
 * Created by Stone on 2016/1/7.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class Warehourse {
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
