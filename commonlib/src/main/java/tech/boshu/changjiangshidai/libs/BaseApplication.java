package tech.boshu.changjiangshidai.libs;

import android.app.Application;

import tech.boshu.changjiangshidai.libs.manager.Contextor;


/**
 * Created by allipper on 12/15/2015.
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Contextor.getInstance().init(getApplicationContext());
    }
}
