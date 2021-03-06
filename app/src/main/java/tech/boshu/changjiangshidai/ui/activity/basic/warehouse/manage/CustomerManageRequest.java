package tech.boshu.changjiangshidai.ui.activity.basic.warehouse.manage;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.manage.CustomerManageActivity;

/**
 * Created by allipper on 16/1/26.
 */
public class CustomerManageRequest extends ServerRequest {

    //仓库新增/修改
    public static void query(
            CustomerManageParam param,
            ResponseCallback<ResponseCustomer> callback) {
        String url = apiHost + "/iv_walHousePage.action";
        GsonRequest<ResponseCustomer> request = new GsonRequest<>(Request.Method.POST,
                url, ResponseCustomer.class, null, param.toQueryParam(), callback, callback);
        HttpUtils.getInstance().request(WarehouseManageActivity.class.getSimpleName(), request);
    }

}
