package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasereturnhistory;

import com.android.volley.Request;

import tech.boshu.changjiangshidai.bean.api.ServerRequest;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.utils.SubUtils;

/**
 * Created by allipper on 16/1/31.
 */
public class PurchaseReturnRequest extends ServerRequest {

    /**
     * @param param    请求参数封装类
     * @param callback
     */
    //1.采购退货历史列表
    public static void query(
            PurchaseRequestParam param,
            ResponseCallback<ResponsePurchaseHistory> callback) {


        GsonRequest<ResponsePurchaseHistory> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/prReturn_prReturnPage.action", ResponsePurchaseHistory.class, null, SubUtils.toParam(param), callback, callback);
        HttpUtils.getInstance().request(PurchaseHistoryActivity.class.getSimpleName(), request);
    }
}
