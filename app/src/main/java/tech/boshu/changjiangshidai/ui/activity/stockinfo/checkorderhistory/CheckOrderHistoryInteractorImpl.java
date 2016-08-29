package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory;

import tech.boshu.changjiangshidai.bean.api.StoreRequest;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)CheckOrderHistoryInteractorImpl.java
 */


public class CheckOrderHistoryInteractorImpl implements
        ICheckOrderHistoryInteractor {
    @Override
    public void queryHistoryList(int storeId,
                                 String orderId,
                                 String beginTime,
                                 String endTime,
                                 ResponseCallback<ResponseCheckOrderHistory> callback) {
        RequestCheckOrderHistoryParam param = new RequestCheckOrderHistoryParam();
        param.companyId = "2222";
        param.storeId = storeId;
        param.orderId = orderId;
        param.beginTime = beginTime;
        param.endTime = endTime;
        StoreRequest.queryCheckOrderHistory(param, callback);
    }
}