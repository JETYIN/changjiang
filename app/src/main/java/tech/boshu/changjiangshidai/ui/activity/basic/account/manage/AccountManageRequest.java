package tech.boshu.changjiangshidai.ui.activity.basic.account.manage;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.AddPurchaseOrderActivity;

/**
 * Created by allipper on 16/1/26.
 */
public class AccountManageRequest extends ServerRequest {

    //3.采购单新增/修改
    public static void query(
            AccountManageParam param,
            ResponseCallback<ResponseAccounts> callback) {
        String url = apiHost + "/ac_accountPage.action";
        GsonRequest<ResponseAccounts> request = new GsonRequest<>(Request.Method.POST,
                url, ResponseAccounts.class, null, param.toQueryParam(), callback, callback);
        HttpUtils.getInstance().request(AddPurchaseOrderActivity.class.getSimpleName(), request);
    }

}
