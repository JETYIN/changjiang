package tech.boshu.changjiangshidai.application;

import tech.boshu.changjiangshidai.libs.BaseApplication;
import tech.boshu.changjiangshidai.ui.activity.start.ResponseUserInfo;

/**
 * Created by allipper on 2016-01-01.
 */
public class ApplicationInit extends BaseApplication {

    private static ApplicationInit app = null;

    public static ResponseUserInfo.DataEntity.ResObjEntity userInfo;
    @Override
    public void onCreate() {
        super.onCreate();
        app= this;
    }


    public void exit() {

        System.exit(0);
    }

    public static ApplicationInit getApp() {
        return app;
    }
}
