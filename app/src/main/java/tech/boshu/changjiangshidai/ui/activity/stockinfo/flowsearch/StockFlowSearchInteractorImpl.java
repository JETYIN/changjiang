package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearch;

import tech.boshu.changjiangshidai.bean.api.StoreRequest;
import tech.boshu.changjiangshidai.bean.result.ResponseSearchCondition;
import tech.boshu.changjiangshidai.libs.activity.BaseInteractorImpl;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockFlowSearchInteractorImpl.java
 */


public class StockFlowSearchInteractorImpl extends BaseInteractorImpl implements
        IStockFlowSearchInteractor {

    @Override
    public void querySearchCondition(ResponseCallback<ResponseSearchCondition> callback) {
        StoreRequest.querySearchCondition(callback);
    }
}