package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchasereturnorder;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.bean.result.PurchaseResult;
import tech.boshu.changjiangshidai.constants.ParameterConstants;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * Created by allipper on 16/1/24.
 */
public class AddPurchaseOrderRequest extends ServerRequest{

    public static void queryPurchaseOrderInfor(AddPurchaseOrderParam params,
            ResponseCallback<ResponseAddPurchaseQureyBean> callback){

        GsonRequest<ResponseAddPurchaseQureyBean> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/prReturn_orderReturnEdit.action", ResponseAddPurchaseQureyBean.class, null, params.toQueryParam(),
                callback, callback);
        HttpUtils.getInstance().request(AddPurchaseOrderActivity.class.getSimpleName(), request);
    }
    //3.采购单新增/修改
    public static void createOrEditPurchase(
            AddPurchaseOrderParam param,
            ResponseCallback<PurchaseResult> callback) {
        String url = apiHost;
        if(param.action.equals(ParameterConstants.ACTION_TYPE_EDIT)){
            url+="/prReturn_updateReturnOrder.action";
        }else if(param.action.equals(ParameterConstants.ACTION_TYPE_ADD)){
            url+="/prReturn_saveReturnOrder.action";
        }
        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                url, PurchaseResult.class, null, param.toParam(), callback, callback);
        HttpUtils.getInstance().request(AddPurchaseOrderActivity.class.getSimpleName(), request);
    }

    //3.采购单新增/修改
    public static void purchase(
            AddPurchaseOrderParam param,
            ResponseCallback<PurchaseResult> callback) {
        String url = apiHost + "/prReturn_prReturn.action";
        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                url, PurchaseResult.class, null, param.toParam(), callback, callback);
        HttpUtils.getInstance().request(AddPurchaseOrderActivity.class.getSimpleName(), request);
    }

    //3.取消
    public static void cancel(
            AddPurchaseOrderParam param,
            ResponseCallback<PurchaseResult> callback) {
        String url = apiHost + "/pr_revoke.action";
        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                url, PurchaseResult.class, null, param.toCancelParam(), callback, callback);
        HttpUtils.getInstance().request(AddPurchaseOrderActivity.class.getSimpleName(), request);
    }
}
