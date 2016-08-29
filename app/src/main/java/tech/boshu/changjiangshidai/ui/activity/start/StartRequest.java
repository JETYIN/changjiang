package tech.boshu.changjiangshidai.ui.activity.start;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.start.ResponseAuth;
import tech.boshu.changjiangshidai.ui.activity.start.ResponseUserInfo;

/**
 * Created by allipper on 16/1/20.
 */
public class StartRequest {


    //1.获取用户信息
    public static void getUserInfo(
            ResponseAuth param,
            ResponseCallback<ResponseUserInfo> callback) {
        GsonRequest<ResponseUserInfo> request = new GsonRequest<>(Request.Method.GET, param.toUrl(), ResponseUserInfo.class, null, null, callback, callback);
        HttpUtils.getInstance().request("queryStoreHistory", request);
    }
}
