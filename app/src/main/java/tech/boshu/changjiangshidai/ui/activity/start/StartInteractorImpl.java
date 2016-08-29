package tech.boshu.changjiangshidai.ui.activity.start;

import android.app.Activity;
import android.content.Intent;

import com.wafersystems.wfauthorize.authorize.WaferLogin;

import tech.boshu.changjiangshidai.application.ApplicationInit;
import tech.boshu.changjiangshidai.libs.activity.BaseInteractorImpl;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.home.HomeActivity;
import tech.boshu.changjiangshidai.utils.SharePre;
import tech.boshu.changjiangshidai.utils.SharePreferenceUtils;


/**
 * Created by allipper on 2016-01-01.
 */
public class StartInteractorImpl extends BaseInteractorImpl implements IStartInteractor {

    public StartInteractorImpl() {
    }

    public Activity activity;

    @Override
    public boolean enter() {
        boolean isGuide =  SharePreferenceUtils.getBoolean(SharePre.App.ISGUIDE, false);
        Intent intent = new Intent(activity, isGuide ? HomeActivity.class :
                GuideActivity.class);
        activity.startActivity(intent);
        activity.finish();
        return  true;
    }

//    @Override
//    public void init(WaferLogin.OnAuthResultListener listener) {
//        enter();
////        WaferLogin.getInstance(activity, "CJTCERP", "C0E81A45AEABEF92").authorize(listener);
//    }

    @Override
    public void getUserInfo(ResponseAuth responseAuth, ResponseCallback<ResponseUserInfo> callBack) {
        StartRequest.getUserInfo(responseAuth, callBack);
    }

    @Override
    public void saveUserInfo(ResponseUserInfo.DataEntity data) {
        ApplicationInit.userInfo = data.resObj;
    }

    @Override
    public void setActivityContext(Activity context) {
        activity = context;
    }
}
