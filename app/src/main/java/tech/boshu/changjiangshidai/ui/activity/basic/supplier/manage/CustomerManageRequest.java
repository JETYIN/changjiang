package tech.boshu.changjiangshidai.ui.activity.basic.supplier.manage;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * Created by allipper on 16/1/26.
 */
public class CustomerManageRequest extends ServerRequest {

    //3.查询
    public static void query(
            CustomerManageParam param,
            ResponseCallback<ResponseCustomer> callback) {
        String url = apiHost + "/pr_providerPage.action";
        GsonRequest<ResponseCustomer> request = new GsonRequest<>(Request.Method.POST,
                url, ResponseCustomer.class, null, param.toQueryParam(), callback, callback);
        HttpUtils.getInstance().request(SupplierManageActivity.class.getSimpleName(), request);
    }

}
