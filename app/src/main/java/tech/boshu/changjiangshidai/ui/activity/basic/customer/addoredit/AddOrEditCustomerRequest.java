package tech.boshu.changjiangshidai.ui.activity.basic.customer.addoredit;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.basic.customer.manage.CustomerManageParam;

/**
 * Created by allipper on 16/1/26.
 */
public class AddOrEditCustomerRequest extends ServerRequest {

    //3.准备数据
    public static void prepareDatas(
            CustomerManageParam param,
            ResponseCallback<ResponseAddOrEditCustomer> callback) {
        String url = apiHost + "/cs_editCustomer.action";
        GsonRequest<ResponseAddOrEditCustomer> request = new GsonRequest<>(Request.Method.POST,
                url, ResponseAddOrEditCustomer.class, null, param.toPreparedParam(), callback, callback);
        HttpUtils.getInstance().request(AddOrEditCustomerActivity.class.getSimpleName(), request);
    }

    public static void addOrEdit(
            CustomerManageParam param,
            ResponseCallback<ResponseAddOrEditCustomer> callback) {
        String url = null;
        if(param.action.equals(ParameterConstants.ACTION_TYPE_ADD)){
            url = apiHost + "/cs_saveAddCustomer.action";
        }else{
            url = apiHost + "/cs_saveEditCustomer.action";
        }
        GsonRequest<ResponseAddOrEditCustomer> request = new GsonRequest<>(Request.Method.POST,
                url, ResponseAddOrEditCustomer.class, null, param.toAddParam(), callback, callback);
        HttpUtils.getInstance().request(AddOrEditCustomerActivity.class.getSimpleName(), request);
    }

}
