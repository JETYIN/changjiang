package tech.boshu.changjiangshidai.bean;

import com.google.gson.Gson;

/**
 * Created by apple on 16/1/7.
 */
public class Supplier {

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
