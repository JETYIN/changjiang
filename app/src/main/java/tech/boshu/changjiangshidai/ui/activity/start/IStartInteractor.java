package tech.boshu.changjiangshidai.ui.activity.start;

import android.app.Activity;

import com.wafersystems.wfauthorize.authorize.WaferLogin;

import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * Created by allipper on 2016-01-01.
 */
public interface IStartInteractor extends IBaseInteractor {

    /**
     * 进入应用
     */
    boolean enter();

    /**
     * 初始化应用
     */
//    void init(WaferLogin.OnAuthResultListener listener);

    void getUserInfo(ResponseAuth responseAuth, ResponseCallback<ResponseUserInfo> callBack);

    void saveUserInfo(ResponseUserInfo.DataEntity data);

    void setActivityContext(Activity context);
}
