package tech.boshu.changjiangshidai.libs.manager;

import android.content.Context;

/**
 * @author allipper
 */
public class Contextor {

    private static Contextor instance;

    public static Contextor getInstance() {
        if (instance == null)
            instance = new Contextor();
        return instance;
    }

    private Context mContext;

    public void init(Context context) {
        this.mContext = context;
    }

    public Context getContext() {
        return mContext;
    }

}
