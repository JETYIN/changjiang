package tech.boshu.changjiangshidai.bean.api;

import android.text.TextUtils;

import com.android.volley.Request;

import java.util.HashMap;
import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.CreatPurchaseReturnedBean;
import tech.boshu.changjiangshidai.bean.mode.OrderDetail;
import tech.boshu.changjiangshidai.bean.mode.ResponsePurchaseReturnedHistroy;
import tech.boshu.changjiangshidai.bean.result.PurchaseResult;
import tech.boshu.changjiangshidai.libs.net.GsonRequest;
import tech.boshu.changjiangshidai.libs.net.HttpUtils;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory.PurchaseRequestParam;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory.ResponsePurchaseHistory;
import tech.boshu.changjiangshidai.utils.SubUtils;

;

/**
 * Created by zoulinlin on 16/1/8.
 */
/*采购相关接口*/
public class PurchaseRequest extends ServerRequest {


    /**
     * @param param    请求参数封装类
     * @param callback
     */
    //1.采购历史列表
    public static void queryPurchaseHistory(
            PurchaseRequestParam param,
            ResponseCallback<ResponsePurchaseHistory> callback) {


        GsonRequest<ResponsePurchaseHistory> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pr_purchasePage.action", ResponsePurchaseHistory.class, null, SubUtils.toParam(param), callback, callback);
        HttpUtils.getInstance().request("queryStoreHistory", request);
    }

    //2.采购单新增 修改页面
    public static void getPurchaseDetail(
            String action,/*判断是新增页面 还是修改页面*/
            String orderId,/*采购单号*/
            ResponseCallback<PurchaseResult> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("action", action);
        params.put("orderId", orderId);

        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pr_orderEdit.action", PurchaseResult.class, null, params, callback, callback);
        HttpUtils.getInstance().request("getPurchaseDetail", request);
    }

    //3.采购单新增
    public static void createPurchase(
            String accountId,
            String companyId,
            int providerId,/*供应商主键	order 采购单*/
            int storeId,/*仓库主键*/
            String setAccountId,/*结算账户主键*/
            double discount,/*整单折扣*/
            double totalMoney,/*应付金额*/
            double payMoney,/*实付金额*/
            String payEndTime,/*截止时间*/
            String createTime,/*业务日期*/
            String memo,/*备注*/
            List<OrderDetail> orderDetailList,
            ResponseCallback<PurchaseResult> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.accountId", accountId);
        params.put("accountDto.companyId", companyId);
        params.put("order.providerId", String.valueOf(providerId));
        params.put("order.storeId", String.valueOf(storeId));
        params.put("order.setAccountId", setAccountId);
        params.put("order.discount", String.valueOf(discount));
        params.put("order.totalMoney", String.valueOf(totalMoney));
        params.put("order.payMoney", String.valueOf(payMoney));
        params.put("order.createTime", createTime);
        params.put("order.payEndTime", payEndTime);
        params.put("order.memo", memo);
        int i = 0;
        for (OrderDetail detail : orderDetailList) {
            params.put("orderDetailList[" + i + "].productId", detail.product);
            params.put("orderDetailList[" + i + "].num", String.valueOf(detail.num));
            params.put("orderDetailList[" + i + "].discount", String.valueOf(detail.discount));
            params.put("orderDetailList[" + i + "].price", String.valueOf(detail.price));
            i++;
        }

        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pr_saveOrder.action", PurchaseResult.class, null, params, callback, callback);
        HttpUtils.getInstance().request("createPurchase", request);
    }

    //4.采购单修改
    public static void modifyPurchaseOrder(
            String accountId,
            String companyId,
            String id,/*订单编号*/
            int providerId,/*供应商主键	order 采购单*/
            int storeId,/*仓库主键*/
            String settlement,/*结算账户主键*/
            double discount,/*整单折扣*/
            double totalMoney,/*应付金额*/
            double payMoney,/*实付金额*/
            String createTime,/*业务日期*/
            String payEndTime,/*截止时间*/
            String memo,/*备注*/
            List<OrderDetail> orderDetailList,
            ResponseCallback<PurchaseResult> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.accountId", accountId);
        params.put("accountDto.companyId", companyId);
        params.put("order.id", id);
        params.put("order.providerId", String.valueOf(providerId));
        params.put("order.storeId", String.valueOf(storeId));
        params.put("order.settlement", settlement);
        params.put("order.discount", String.valueOf(discount));
        params.put("order.totalMoney", String.valueOf(totalMoney));
        params.put("order.payMoney", String.valueOf(payMoney));
        params.put("order.createTime", createTime);
        params.put("order.payEndTime", payEndTime);
        params.put("order.memo", memo);
        int i = 0;
        for (OrderDetail detail : orderDetailList) {
            params.put("orderDetailList[" + i + "].productId", detail.product);
            params.put("orderDetailList[" + i + "].num", String.valueOf(detail.num));
            i++;
        }

        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pr_saveEditOrder.action", PurchaseResult.class, null, params, callback, callback);
        HttpUtils.getInstance().request("modifyPurchaseOrder", request);
    }

    //从第五个开始按照更新要求
    //5. 采购单采购
    public static void buyOfPurchase(
            String accountId,
            String companyId,
            String id,
            int providerId,/*供应商主键	order 采购单*/
            int storeId,/*仓库主键*/
            String setAccountId,/*结算账户主键*/
            double discount,/*整单折扣*/
            double totalMoney,/*应付金额*/
            double payMoney,/*实付金额*/
            String createTime,/*业务日期*/
            String memo,/*备注*/
            List<OrderDetail> orderDetailList,
            ResponseCallback<PurchaseResult> callback
    ) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("order.id", id);
        params.put("accountDto.accountId", accountId);
        params.put("accountDto.companyId", companyId);
        params.put("order.providerId", String.valueOf(providerId));
        params.put("order.storeId", String.valueOf(storeId));
        params.put("order.setAccountId", setAccountId);
        params.put("order.discount", String.valueOf(discount));
        params.put("order.totalMoney", String.valueOf(totalMoney));
        params.put("order.payMoney", String.valueOf(payMoney));
        params.put("order.createTime", createTime);
        params.put("order.memo", memo);
        int i = 0;
        for (OrderDetail detail : orderDetailList) {
            params.put("orderDetailList[" + i + "].productId", detail.product);
            params.put("orderDetailList[" + i + "].num", String.valueOf(detail.num));
            i++;
        }

        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pr_purchase.action", PurchaseResult.class, null, params, callback, callback);
        HttpUtils.getInstance().request("buyOfPurchase", request);
    }

    //6.采购单复制
    public static void copyPurchaseOrder(
            String orderId,/*订单编号*/
            ResponseCallback<PurchaseResult> callback) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("orderId", orderId);

        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/pr_copy.action", PurchaseResult.class, null, params, callback, callback);
        HttpUtils.getInstance().request("copyPurchaseOrder", request);
    }

    //7.采购单退货历史列表
    public static void purchaseReturnedOrderList(
            String status, /*状态*/
            String companyId,/*公司*/
            int providerId,/*供应商id*/
            String beginTime,/*开始时间 2015-12-28 17:35:00*/
            String endTime,/*结束时间*/
            String orderId,/*订单编号*/
            ResponseCallback<ResponsePurchaseReturnedHistroy> callback) {
        final HashMap<String, String> params = new HashMap<>();

        if (!TextUtils.isEmpty(status)) {
            params.put("status", String.valueOf(status));
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
        params.put("providerId", String.valueOf(providerId));


        params.put("orderId", orderId);
        GsonRequest<ResponsePurchaseReturnedHistroy> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/prReturn_prReturnPage.action", ResponsePurchaseReturnedHistroy.class, null, params, callback, callback);
        HttpUtils.getInstance().request("purchaseReturnedOrderList", request);
    }

    //8.采购退货单新增页面 修改页面
    public static void addOrModifyPurchaseReturnedPage(
            String action,/*新增页面*/
            String orderId,
            ResponseCallback<CreatPurchaseReturnedBean> callback
    ) {
        final HashMap<String, String> params = new HashMap<>();

            params.put("action", action);


            params.put("orderId", orderId);



        GsonRequest<CreatPurchaseReturnedBean> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/prReturn_orderReturnEdit.action", CreatPurchaseReturnedBean.class, null, params, callback, callback);
        HttpUtils.getInstance().request("addOrModifyPurchaseReturnedPage", request);
    }


    //9.采购退货单新增
    public static void addPurchaseReturned(
            String accountId,
            String companyId,
            int providerId,/*供应商主键	order 采购单*/
            int storeId,/*仓库主键*/
            String setAccountId,/*结算账户主键*/
            double discount,/*整单折扣*/
            double totalMoney,/*应付金额*/
            double payMoney,/*实付金额*/
            String createTime,/*业务日期*/
            String memo,/*备注*/
            List<OrderDetail> orderDetailList,
            ResponseCallback<PurchaseResult> callback
    ) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("accountDto.accountId", accountId);
        params.put("accountDto.companyId", companyId);
        params.put("order.providerId", String.valueOf(providerId));
        params.put("order.storeId", String.valueOf(storeId));
        params.put("order.setAccountId", setAccountId);
        params.put("order.discount", String.valueOf(discount));
        params.put("order.totalMoney", String.valueOf(totalMoney));
        params.put("order.payMoney", String.valueOf(payMoney));
        params.put("order.createTime", createTime);
        params.put("order.memo", memo);
        int i = 0;
        for (OrderDetail detail : orderDetailList) {
            params.put("orderDetailList[" + i + "].productId", detail.product);
            params.put("orderDetailList[" + i + "].num", String.valueOf(detail.num));
            i++;
        }

        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/prReturn_saveReturnOrder.action", PurchaseResult.class, null, params, callback, callback);
        HttpUtils.getInstance().request("addPurchaseReturnedv", request);
    }

    //10.采购退货单修改
    public static void modifyPurchaseReturned(
            String id,
            int providerId,/*供应商主键	order 采购单*/
            int storeId,/*仓库主键*/
            String setAccountId,/*结算账户主键*/
            double discount,/*整单折扣*/
            double totalMoney,/*应付金额*/
            double payMoney,/*实付金额*/
            String createTime,/*业务日期*/
            String memo,/*备注*/
            List<OrderDetail> orderDetailList,
            ResponseCallback<PurchaseResult> callback
    ) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("order.id", id);
        params.put("order.providerId", String.valueOf(providerId));
        params.put("order.storeId", String.valueOf(storeId));
        params.put("order.setAccountId", setAccountId);
        params.put("order.discount", String.valueOf(discount));
        params.put("order.totalMoney", String.valueOf(totalMoney));
        params.put("order.payMoney", String.valueOf(payMoney));
        params.put("order.createTime", createTime);
        params.put("order.memo", memo);
        int i = 0;
        for (OrderDetail detail : orderDetailList) {
            params.put("orderDetailList[" + i + "].productId", detail.product);
            params.put("orderDetailList[" + i + "].num", String.valueOf(detail.num));
            i++;
        }

        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/prReturn_updateReturnOrder.action", PurchaseResult.class, null, params, callback, callback);
        HttpUtils.getInstance().request("modifyPurchaseReturned", request);
    }

    //11.采购单退货
    public static void purchaseReturned(
            String id,
            int providerId,/*供应商主键	order 采购单*/
            int storeId,/*仓库主键*/
            String setAccountId,/*结算账户主键*/
            double discount,/*整单折扣*/
            double totalMoney,/*应付金额*/
            double payMoney,/*实付金额*/
            String createTime,/*业务日期*/
            String memo,/*备注*/
            List<OrderDetail> orderDetailList,
            ResponseCallback<PurchaseResult> callback
    ) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("order.id", id);
        params.put("order.providerId", String.valueOf(providerId));
        params.put("order.storeId", String.valueOf(storeId));
        params.put("order.setAccountId", setAccountId);
        params.put("order.discount", String.valueOf(discount));
        params.put("order.totalMoney", String.valueOf(totalMoney));
        params.put("order.payMoney", String.valueOf(payMoney));
        params.put("order.createTime", createTime);
        params.put("order.memo", memo);
        for (OrderDetail detail : orderDetailList) {
            params.put("orderDetailList.productId", detail.product);
            params.put("orderDetailList.num", String.valueOf(detail.num));

        }

        GsonRequest<PurchaseResult> request = new GsonRequest<>(Request.Method.POST,
                apiHost + "/prReturn_prReturn.action", PurchaseResult.class, null, params, callback, callback);
        HttpUtils.getInstance().request("purchaseReturned", request);
    }

}