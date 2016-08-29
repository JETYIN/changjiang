package tech.boshu.changjiangshidai.ui.activity.basic.setting.personinfo;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * Created by allipper on 16/1/26.
 */
public class AccountRequest extends ServerRequest {

    //3.采购单新增/修改
    public static void add(
            AccountParams param,
            ResponseCallback<AccountParams> callback) {
        String url = apiHost + "/ac_savepersonInfor.action";
        GsonRequest<AccountParams> request = new GsonRequest<>(Request.Method.POST,
                url, AccountParams.class, null, param.toAddParam(), callback, callback);
        HttpUtils.getInstance().request(PersonInfoActivity.class.getSimpleName(), request);
    }

}
