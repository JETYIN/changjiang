package tech.boshu.changjiangshidai.bean.api;

import com.android.volley.Request;

import java.util.HashMap;

import tech.boshu.changjiangshidai.bean.result.ResponseCheckDetail;
import tech.boshu.changjiangshidai.bean.result.ResponseSearchCondition;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail.RequestCheckDetailParam;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory
        .RequestCheckOrderHistoryParam;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory
        .ResponseCheckOrderHistory;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult.RequestStockFlowParam;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult.ResponseStockFlow;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock.RequestProductParam;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock.ResponseProductStockInfo;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult.RequestStoreInfoParam;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult.ResponseStoreInfo;

;

/**
 * Created by zoulinlin on 16/1/8.
 * 库存相关接口
 */
public class StoreRequest extends ServerRequest {

    //获取查询条件
    public static void querySearchCondition(ResponseCallback<ResponseSearchCondition> callback) {
        HashMap<String, String> params = new HashMap<>();
        params.put("companyId", "2222");
        GsonRequest<ResponseSearchCondition> request = new GsonRequest<>(
                Request.Method.POST,
                apiHost + "/searchPage.action",
                ResponseSearchCondition.class,
                null,
                params,
                callback,
                callback);
        HttpUtils.getInstance().request("querySearchData", request);
    }

    //查询库存
    public static void queryStore(
            RequestStoreInfoParam params,
            ResponseCallback<ResponseStoreInfo> callback) {

        GsonRequest<ResponseStoreInfo> request = new GsonRequest<>(
                Request.Method.POST,
                apiHost + "/iv_storePage.action",
                ResponseStoreInfo.class,
                null,
                params.toParam(),
                callback,
                callback);
        HttpUtils.getInstance().request("queryStore", request);
    }

    //查询单个商品的库存
    public static void queryProductStore(
            RequestProductParam params,
            ResponseCallback<ResponseProductStockInfo> callback) {

        GsonRequest<ResponseProductStockInfo> request = new GsonRequest<>(
                Request.Method.POST,
                apiHost + "/iv_productStore.action",
                ResponseProductStockInfo.class,
                null,
                params.toParam(),
                callback,
                callback);
        HttpUtils.getInstance().request("queryProductStore", request);
    }


    //查询库存流水
    public static void queryStoreFlow(
            RequestStockFlowParam param,
            ResponseCallback<ResponseStockFlow> callback) {

        GsonRequest<ResponseStockFlow> request = new GsonRequest<>(
                Request.Method.POST,
                apiHost + "/iv_storeFl.action",
                ResponseStockFlow.class,
                null,
                param.toParam(),
                callback,
                callback);
        HttpUtils.getInstance().request("queryStoreFlow", request);
    }

    //查询盘点历史
    public static void queryCheckOrderHistory(
            RequestCheckOrderHistoryParam param,
            ResponseCallback<ResponseCheckOrderHistory> callback) {

        GsonRequest<ResponseCheckOrderHistory> request = new GsonRequest<>(
                Request.Method.POST,
                apiHost + "/iv_checkPage.action",
                ResponseCheckOrderHistory.class,
                null,
                param.toParam(),
                callback,
                callback);
        HttpUtils.getInstance().request("queryCheckOrderHistory", request);
    }

    //盘点单详情
    public static void queryCheckOrderDetail(
            RequestCheckDetailParam param,
            ResponseCallback<ResponseCheckDetail> callback) {
        GsonRequest<ResponseCheckDetail> request = new GsonRequest<>(
                Request.Method.POST,
                apiHost + "/iv_checkOrderEdit.action",
                ResponseCheckDetail.class,
                null,
                param.toParam(),
                callback,
                callback);
        HttpUtils.getInstance().request("queryCheckOrderHistory", request);
    }
}

