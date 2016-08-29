package tech.boshu.changjiangshidai.ui.activity.basic.setting.starfmanage;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder.AddPurchaseOrderActivity;

/**
 * Created by allipper on 16/1/26.
 */
public class StaffManageRequest extends ServerRequest {

    //3.采购单新增/修改
    public static void query(
            StaffManageParam param,
            ResponseCallback<ResponseStaff> callback) {
        String url = apiHost + "/ac_accountPage.action";
        GsonRequest<ResponseStaff> request = new GsonRequest<>(Request.Method.POST,
                url, ResponseStaff.class, null, param.toQueryParam(), callback, callback);
        HttpUtils.getInstance().request(AddPurchaseOrderActivity.class.getSimpleName(), request);
    }

}
