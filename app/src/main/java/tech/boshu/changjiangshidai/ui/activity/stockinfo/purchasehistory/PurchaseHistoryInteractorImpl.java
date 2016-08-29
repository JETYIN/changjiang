package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory;

import tech.boshu.changjiangshidai.bean.api.PurchaseRequest;
import tech.boshu.changjiangshidai.libs.activity.BaseInteractorImpl;
import tech.boshu.changjiangshidai.libs.bean.Pagination;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)PurchaseHistoryInteractorImpl.java
 */


public class PurchaseHistoryInteractorImpl extends BaseInteractorImpl implements IPurchaseHistoryInteractor {

    public PurchaseRequestParam purchaseRequestParam = new PurchaseRequestParam();

    public PurchaseHistoryResponseCallback callBack;


    @Override
    public void getDatas() {
        PurchaseRequest.queryPurchaseHistory(purchaseRequestParam, new ResponseCallback<ResponsePurchaseHistory>() {
            @Override
            public void onRequestSuccess(ResponsePurchaseHistory result) {
                callBack.onRequestSuccess(result);
            }

            @Override
            public void onReuquestFailed(ErrorBase error) {
                callBack.onFailed(error);
            }
        });
    }

    public void filterByDefault() {
        purchaseRequestParam.status = null;
    }

    public void filterByCount() {
        purchaseRequestParam.status = "1";
    }

    public void filterByDraft() {
        purchaseRequestParam.status = "0";
    }

    public void filterByCancel() {
        purchaseRequestParam.status = "2";
    }

    @Override
    public void setPagination(Pagination param) {
        purchaseRequestParam.pageCount = param.pageCount;
        purchaseRequestParam.pageNo = param.pageNo;
        purchaseRequestParam.pageSize = param.pageSize;
        purchaseRequestParam.totalCount = param.totalCount;
    }

    @Override
    public void setOnSueccessCallback(PurchaseHistoryResponseCallback callback) {
        this.callBack = callback;
    }

    @Override
    public void setParams(PurchaseRequestParam params) {
        this.purchaseRequestParam = params;
    }

}