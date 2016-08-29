package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail;

import tech.boshu.changjiangshidai.bean.api.StoreRequest;
import tech.boshu.changjiangshidai.bean.result.ResponseCheckDetail;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)CheckDetailInteractorImpl.java
 */


public class CheckDetailInteractorImpl implements ICheckDetailInteractor {

    @Override
    public void queryCheckDetail(String orderId, ResponseCallback<ResponseCheckDetail> callback) {
        RequestCheckDetailParam param = new RequestCheckDetailParam();
        param.orderId = orderId;
        StoreRequest.queryCheckOrderDetail(param, callback);
    }
}