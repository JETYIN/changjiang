package tech.boshu.changjiangshidai.bean.api;

import android.text.TextUtils;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.Arrears;
import tech.boshu.changjiangshidai.bean.mode.OrderDetailDto;
import tech.boshu.changjiangshidai.bean.mode.PrOrder;
import tech.boshu.changjiangshidai.bean.mode.ProDetail;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.bean.mode.ResponseAddProuct;
import tech.boshu.changjiangshidai.bean.mode.ResponseLossInfor;
import tech.boshu.changjiangshidai.bean.mode.ResponseSaleHistory;
import tech.boshu.changjiangshidai.bean.mode.ResponseSaleOrderInfor;
import tech.boshu.changjiangshidai.libs.bean.ResponseBase;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * Created by Administrator on 2016/1/14.
 */
public class SalesRequest extends ServerRequest {
    //销售历史列表
    public static void querySalesHistory(String status, String companyId, String
            customerId, String orderId, String beginTime, String endTime,
                                         ResponseCallback<ResponseSaleHistory> callback) {
        final HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(status)) {
            params.put("status", status);
        }
        if (!TextUtils.isEmpty(customerId)) {
            params.put("customerId", customerId);
        }

        params.put("orderId", orderId);
        params.put("companyId", companyId);
        if (!TextUtils.isEmpty(beginTime)) {
            params.put("beginTime", beginTime);
        }
        if (!TextUtils.isEmpty(endTime)) {
            params.put("endTime", endTime);
        }

        GsonRequest<ResponseSaleHistory> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/sl_salePage.action", ResponseSaleHistory.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("querySalesHistory", request);
    }

    //销售单新增/修改页面数据
    public static void querySalesOrderIfor(String action, String orderId,
                                           ResponseCallback<ResponseSaleOrderInfor> callback) {

        final HashMap<String, String> params = new HashMap<>();
        params.put("action", action);
        if (!TextUtils.isEmpty(orderId)) {
            params.put("orderId", orderId);
        }
        GsonRequest<ResponseSaleOrderInfor> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/sl_orderEdit.action", ResponseSaleOrderInfor.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("querySalesOrderIfor", request);
    }

    //获取客户尚欠款
    public static void queruArrears(String customerId, ResponseCallback<Arrears>
            callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("customerId", customerId);
        GsonRequest<Arrears> request = new GsonRequest<>(Request.Method.POST, apiHost +
                "/sl_arrears.action", Arrears.class, null, params, callback, callback);
        HttpUtils.getInstance().request("queruArrears", request);
    }

    //获取能添加商品清单
    public static void queruProductList(String companyId, String strId, String
            catalogId, String brandId, String status, ResponseCallback<ResponseAddProuct>
                                                callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("companyId", companyId);
        if (!TextUtils.isEmpty(strId)) {
            params.put("strId", strId);
        }
        if (!TextUtils.isEmpty(catalogId)) {
            params.put("catalogId", catalogId);
        }
        if (!TextUtils.isEmpty(brandId)) {
            params.put("brandId", brandId);
        }
        if (!TextUtils.isEmpty(status)) {
            params.put("status", status);
        }
        GsonRequest<ResponseAddProuct> request = new GsonRequest<>(Request
                .Method.POST, apiHost + "/pd_productPage.action", ResponseAddProuct.class, null,
                params, callback, callback);
        HttpUtils.getInstance().request("queruProductList", request);
    }

    //获取商品详情
    public static void queruProductDetils(String action, int id, int
            storeId, ResponseCallback<ProDetail> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("action", action);
        params.put("id", String.valueOf(id));
        params.put("storeId", String.valueOf(storeId));
        GsonRequest<ProDetail> request = new GsonRequest<>(Request
                .Method.POST, apiHost + "/pd_editProduct.action", ProDetail.class, null,
                params, callback, callback);
        HttpUtils.getInstance().request("queruProductList", request);
    }

    //销售订单出售
    public static void sellOrder(String companyId, String accountId, String action, PrOrder
            order, List<OrderDetailDto> orderDetailList, ResponseCallback<ResponseBase> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.companyId", companyId);
        params.put("accountDto.accountId", accountId);
        params.put("action", action);
        if (!TextUtils.isEmpty(order.id)) {
            params.put("order.id", order.id);
        }
        params.put("order.customerId", order.customerId);
        params.put("order.storeId", String.valueOf(order.storeId));
        params.put("order.settlement", String.valueOf(order.settlement));
        params.put("order.sendType", String.valueOf(order.sendType));
        params.put("order.discount", String.valueOf(order.discount));
        params.put("order.getMoney", String.valueOf(order.getMoney));
        params.put("order.totalNum", String.valueOf(order.totalNum));
        if (!TextUtils.isEmpty(String.valueOf(order.getMoney))) {
            params.put("order.totalMoney", String.valueOf(order.totalMoney));
        }
        if (!TextUtils.isEmpty(order.payEndTimeStr)) {
            params.put("order.payEndTime", order.payEndTimeStr);
        }
        params.put("order.createTime", String.valueOf(order.createTime));
        if (!TextUtils.isEmpty(order.memo)) {
            params.put("order.memo", order.memo);
        }
        for (int i = 0; i < orderDetailList.size(); i++) {
            params.put("orderDetailList[" + String.valueOf(i) + "].productId", String.valueOf
                    (orderDetailList.get(i).productId));
            params.put("orderDetailList[" + String.valueOf(i) + "].num", String.valueOf
                    (orderDetailList.get(i).num));
            params.put("orderDetailList[" + String.valueOf(i) + "].discount", String.valueOf
                    (orderDetailList.get(i).discount));
            params.put("orderDetailList[" + String.valueOf(i) + "].price", String.valueOf
                    (orderDetailList.get(i).price));
        }
        GsonRequest<ResponseBase> request = new GsonRequest<>(Request
                .Method.POST, apiHost + "/sl_sale.action", ResponseBase.class, null,
                params, callback, callback);
        HttpUtils.getInstance().request("sellOrder", request);
    }

    //销售退货历史
    public static void queryReturnHistory(String status, String companyId, int
            customerId, String orderId, String beginTime, String endTime,
                                          ResponseCallback<ResponseSaleHistory> callback) {
        final HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(status)) {
            params.put("status", status);
        }
        params.put("customerId", String.valueOf(customerId));
        params.put("orderId", orderId);
        params.put("companyId", companyId);
        if (!TextUtils.isEmpty(beginTime)) {
            params.put("beginTime", beginTime);
        }
        if (!TextUtils.isEmpty(endTime)) {
            params.put("endTime", endTime);
        }

        GsonRequest<ResponseSaleHistory> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/sl_slReturnPage.action", ResponseSaleHistory.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("queryReturnHistory", request);
    }

    //销售退货单新增/修改页面
    public static void queryReturnOrderIfor(String action, String orderId,
                                            ResponseCallback<ResponseSaleOrderInfor> callback) {

        final HashMap<String, String> params = new HashMap<>();
        params.put("action", action);
        if (!TextUtils.isEmpty(orderId)) {
            params.put("orderId", orderId);
        }
        GsonRequest<ResponseSaleOrderInfor> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/sl_orderReturnEdit.action", ResponseSaleOrderInfor.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("queryReturnOrderIfor", request);
    }

    //销售单查稿（修改后保存为草稿）
    public static void saveEditDraft(String companyId, String accountId, PrOrder order,
                                     List<OrderDetailDto> orderDetailList,
                                     ResponseCallback<ResponseBase> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.companyId", companyId);
        params.put("accountDto.accountId", accountId);
        if (!TextUtils.isEmpty(order.id)) {
            params.put("order.id", order.id);
        }
        params.put("order.customerId", order.customerId);
        params.put("order.storeId", String.valueOf(order.storeId));
        params.put("order.settlement", String.valueOf(order.settlement));
        params.put("order.sendType", String.valueOf(order.sendType));
        params.put("order.discount", String.valueOf(order.discount));
        params.put("order.getMoney", String.valueOf(order.getMoney));
        params.put("order.totalNum", String.valueOf(order.totalNum));
        if (!TextUtils.isEmpty(String.valueOf(order.getMoney))) {
            params.put("order.totalMoney", String.valueOf(order.totalMoney));
        }
        if (!TextUtils.isEmpty(order.payEndTimeStr)) {
            params.put("order.payEndTime", order.payEndTimeStr);
        }
        params.put("order.createTime", String.valueOf(order.createTime));
        if (!TextUtils.isEmpty(order.memo)) {
            params.put("order.memo", order.memo);
        }
        for (int i = 0; i < orderDetailList.size(); i++) {
            params.put("orderDetailList[" + String.valueOf(i) + "].productId", String.valueOf
                    (orderDetailList.get(i).productId));
            params.put("orderDetailList[" + String.valueOf(i) + "].num", String.valueOf
                    (orderDetailList.get(i).num));
            params.put("orderDetailList[" + String.valueOf(i) + "].discount", String.valueOf
                    (orderDetailList.get(i).discount));
            params.put("orderDetailList[" + String.valueOf(i) + "].price", String.valueOf
                    (orderDetailList.get(i).price));
        }
        GsonRequest<ResponseBase> request = new GsonRequest<>(Request
                .Method.POST, apiHost + "/sl_saveEditOrder.action", ResponseBase.class, null,
                params, callback, callback);
        HttpUtils.getInstance().request("saveEditDraft", request);
    }

    //销售单草稿（新增后保存为草稿）
    public static void saveAddDraft(String companyId, String accountId, PrOrder order,
                                    List<OrderDetailDto> orderDetailList,
                                    ResponseCallback<ResponseBase> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.companyId", companyId);
        params.put("accountDto.accountId", accountId);
        params.put("order.customerId", order.customerId);
        params.put("order.storeId", String.valueOf(order.storeId));
        params.put("order.settlement", String.valueOf(order.settlement));
        params.put("order.sendType", String.valueOf(order.sendType));
        params.put("order.discount", String.valueOf(order.discount));
        params.put("order.getMoney", String.valueOf(order.getMoney));
        params.put("order.totalNum", String.valueOf(order.totalNum));
        if (!TextUtils.isEmpty(String.valueOf(order.getMoney))) {
            params.put("order.totalMoney", String.valueOf(order.totalMoney));
        }
        if (!TextUtils.isEmpty(order.payEndTimeStr)) {
            params.put("order.payEndTime", order.payEndTimeStr);
        }
        params.put("order.createTime", String.valueOf(order.createTime));
        if (!TextUtils.isEmpty(order.memo)) {
            params.put("order.memo", order.memo);
        }
        for (int i = 0; i < orderDetailList.size(); i++) {
            params.put("orderDetailList[" + String.valueOf(i) + "].productId", String.valueOf
                    (orderDetailList.get(i).productId));
            params.put("orderDetailList[" + String.valueOf(i) + "].num", String.valueOf
                    (orderDetailList.get(i).num));
            params.put("orderDetailList[" + String.valueOf(i) + "].discount", String.valueOf
                    (orderDetailList.get(i).discount));
            params.put("orderDetailList[" + String.valueOf(i) + "].price", String.valueOf
                    (orderDetailList.get(i).price));
        }
        GsonRequest<ResponseBase> request = new GsonRequest<>(Request
                .Method.POST, apiHost + "/sl_saveOrder.action", ResponseBase.class, null,
                params, callback, callback);
        HttpUtils.getInstance().request("saveEditDraft", request);
    }

    //复制销售单
    public static void copySaleOrder(String orderId, ResponseCallback<ResponseSaleOrderInfor>
            callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("orderId", orderId);
        GsonRequest<ResponseSaleOrderInfor> request = new GsonRequest<>(Request
                .Method.POST, apiHost + "/sl_copy.action", ResponseSaleOrderInfor.class, null,
                params, callback, callback);
        HttpUtils.getInstance().request("copySaleOrder", request);
    }

    //销售退货单新增/修改页面
    public static void queryRetrunOrderIfor(String action, String orderId,
                                            ResponseCallback<ResponseSaleOrderInfor> callback) {

        final HashMap<String, String> params = new HashMap<>();
        params.put("action", action);
        if (!TextUtils.isEmpty(orderId)) {
            params.put("orderId", orderId);
        }
        GsonRequest<ResponseSaleOrderInfor> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/sl_orderReturnEdit.action", ResponseSaleOrderInfor.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("queryRetrunOrderIfor", request);
    }

    //销售退货单草稿（新增后保存为草稿）
    public static void saveAddReturnDraft(String companyId, String accountId, PrOrder order,
                                          List<OrderDetailDto> orderDetailList,
                                          ResponseCallback<ResponseBase> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.companyId", companyId);
        params.put("accountDto.accountId", accountId);
        params.put("order.customerId", order.customerId);
        params.put("order.storeId", String.valueOf(order.storeId));
        params.put("order.settlement", String.valueOf(order.settlement));
        params.put("order.sendType", String.valueOf(order.sendType));
        params.put("order.discount", String.valueOf(order.discount));
        params.put("order.getMoney", String.valueOf(order.getMoney));
        params.put("order.totalNum", String.valueOf(order.totalNum));
        if (!TextUtils.isEmpty(String.valueOf(order.getMoney))) {
            params.put("order.totalMoney", String.valueOf(order.totalMoney));
        }
        if (!TextUtils.isEmpty(order.payEndTimeStr)) {
            params.put("order.payEndTime", order.payEndTimeStr);
        }
        params.put("order.createTime", String.valueOf(order.createTime));
        if (!TextUtils.isEmpty(order.memo)) {
            params.put("order.memo", order.memo);
        }
        for (int i = 0; i < orderDetailList.size(); i++) {
            params.put("orderDetailList[" + String.valueOf(i) + "].productId", String.valueOf
                    (orderDetailList.get(i).productId));
            params.put("orderDetailList[" + String.valueOf(i) + "].num", String.valueOf
                    (orderDetailList.get(i).num));
            params.put("orderDetailList[" + String.valueOf(i) + "].discount", String.valueOf
                    (orderDetailList.get(i).discount));
            params.put("orderDetailList[" + String.valueOf(i) + "].price", String.valueOf
                    (orderDetailList.get(i).price));
        }
        GsonRequest<ResponseBase> request = new GsonRequest<>(Request
                .Method.POST, apiHost + "/sl_saveReturnOrder.action", ResponseBase.class, null,
                params, callback, callback);
        HttpUtils.getInstance().request("saveAddReturnDraft", request);
    }

    //销售订单出售
    public static void returnOrder(String companyId, String accountId, String action, PrOrder
            order, List<OrderDetailDto> orderDetailList, ResponseCallback<ResponseBase> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.companyId", companyId);
        params.put("accountDto.accountId", accountId);
        params.put("action", action);
        if (!TextUtils.isEmpty(order.id)) {
            params.put("order.id", order.id);
        }
        params.put("order.customerId", order.customerId);
        params.put("order.storeId", String.valueOf(order.storeId));
        params.put("order.settlement", String.valueOf(order.settlement));
        params.put("order.sendType", String.valueOf(order.sendType));
        params.put("order.discount", String.valueOf(order.discount));
        params.put("order.getMoney", String.valueOf(order.getMoney));
        params.put("order.totalNum", String.valueOf(order.totalNum));
        if (!TextUtils.isEmpty(String.valueOf(order.getMoney))) {
            params.put("order.totalMoney", String.valueOf(order.totalMoney));
        }
        if (!TextUtils.isEmpty(order.payEndTimeStr)) {
            params.put("order.payEndTime", order.payEndTimeStr);
        }
        params.put("order.createTime", String.valueOf(order.createTime));
        if (!TextUtils.isEmpty(order.memo)) {
            params.put("order.memo", order.memo);
        }
        for (int i = 0; i < orderDetailList.size(); i++) {
            params.put("orderDetailList[" + String.valueOf(i) + "].productId", String.valueOf
                    (orderDetailList.get(i).productId));
            params.put("orderDetailList[" + String.valueOf(i) + "].num", String.valueOf
                    (orderDetailList.get(i).num));
            params.put("orderDetailList[" + String.valueOf(i) + "].discount", String.valueOf
                    (orderDetailList.get(i).discount));
            params.put("orderDetailList[" + String.valueOf(i) + "].price", String.valueOf
                    (orderDetailList.get(i).price));
        }
        GsonRequest<ResponseBase> request = new GsonRequest<>(Request
                .Method.POST, apiHost + "/sl_slReturn.action", ResponseBase.class, null,
                params, callback, callback);
        HttpUtils.getInstance().request("returnOrder", request);
    }

    //损益单历史
    public static void queryGainOrLossHistory(String status, String companyId, String
            customerId, String orderId, String beginTime, String endTime,
                                              ResponseCallback<ResponseSaleHistory> callback) {
        final HashMap<String, String> params = new HashMap<>();
        if (!TextUtils.isEmpty(status)) {
            params.put("status", status);
        }
        if (!TextUtils.isEmpty(customerId)) {
            params.put("customerId", customerId);
        }
        if (!TextUtils.isEmpty(orderId)) {
            params.put("orderId", orderId);
        }
        if (!TextUtils.isEmpty(companyId)) {
            params.put("companyId", companyId);
        }

        if (!TextUtils.isEmpty(beginTime)) {
            params.put("beginTime", beginTime);
        }
        if (!TextUtils.isEmpty(endTime)) {
            params.put("endTime", endTime);
        }

        GsonRequest<ResponseSaleHistory> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pl_profitPage.action", ResponseSaleHistory.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("queryGainOrLossHistory", request);
    }

    //损益单新增，修改页面
    public static void editGainOrLoss(String action, String orderId,
                                      ResponseCallback<ResponseLossInfor> callback) {

        final HashMap<String, String> params = new HashMap<>();
        params.put("action", action);
        if (!TextUtils.isEmpty(orderId)) {
            params.put("orderId", orderId);
        }
        GsonRequest<ResponseLossInfor> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pl_editOrder.action", ResponseLossInfor.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("editGainOrLoss", request);
    }

    //新增损益单草稿
    public static void addLossDraft(String accountId, String companyId, PrOrder prOrder,
                                    List<OrderDetailDto> orderDetailDtos,
                                    ResponseCallback<ResponseBase> callback) {

        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.accountId", accountId);
        params.put("accountDto.companyId", companyId);
        params.put("order.storeId", String.valueOf(prOrder.storeId));
        params.put("order.customerId", String.valueOf(prOrder.customerId));
        params.put("order.settlement", String.valueOf(prOrder.settlement));
        params.put("order.totalNum", String.valueOf(prOrder.totalNum));
        params.put("order.totalMoney", String.valueOf(prOrder.totalMoney));
        params.put("order.memo", prOrder.memo);
        for (int i = 0; i < orderDetailDtos.size(); i++) {
            params.put("orderDetailList[" + i + "].productId", String.valueOf(orderDetailDtos.get
                    (i).productId));
            params.put("orderDetailList[" + i + "].amount", String.valueOf(orderDetailDtos.get(i)
                    .num));
            params.put("orderDetailList[" + i + "].price)", String.valueOf(orderDetailDtos.get(i)
                    .price));
        }
        GsonRequest<ResponseBase> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pl_saveOrder.action", ResponseBase.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("addLossDraft", request);
    }

    //损益单出售
    public static void sellLossOrder(String action, PrOrder prOrder,
                                     List<OrderDetailDto> orderDetailDtos,
                                     ResponseCallback<ResponseBase> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("action", action);
        params.put("order.storeId", String.valueOf(prOrder.storeId));
        params.put("order.customerId", String.valueOf(prOrder.customerId));
        params.put("order.settlement", String.valueOf(prOrder.settlement));
        params.put("order.totalNum", String.valueOf(prOrder.totalNum));
        params.put("order.totalMoney", String.valueOf(prOrder.totalMoney));
        params.put("order.memo", prOrder.memo);
        for (int i = 0; i < orderDetailDtos.size(); i++) {
            params.put("orderDetailList[" + i + "].productId", String.valueOf(orderDetailDtos.get
                    (i).productId));
            params.put("orderDetailList[" + i + "].amount", String.valueOf(orderDetailDtos.get(i)
                    .num));
            params.put("orderDetailList[" + i + "].price", String.valueOf(orderDetailDtos.get(i)
                    .price));
        }
        GsonRequest<ResponseBase> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pl_confirm.action", ResponseBase.class, null, params,
                callback, callback);
        HttpUtils.getInstance().request("sellLossOrder", request);
    }
}
