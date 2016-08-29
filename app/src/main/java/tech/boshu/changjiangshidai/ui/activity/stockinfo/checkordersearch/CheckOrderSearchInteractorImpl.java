package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkordersearch;

import tech.boshu.changjiangshidai.bean.api.StoreRequest;
import tech.boshu.changjiangshidai.bean.result.ResponseSearchCondition;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)CheckOrderSearchInteractorImpl.java
 */


public class CheckOrderSearchInteractorImpl implements ICheckOrderSearchInteractor {

    @Override
    public void querySearchCondition(ResponseCallback<ResponseSearchCondition> callback) {
        StoreRequest.querySearchCondition(callback);
    }
}