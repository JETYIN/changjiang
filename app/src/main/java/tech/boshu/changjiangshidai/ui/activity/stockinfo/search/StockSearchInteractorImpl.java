package tech.boshu.changjiangshidai.ui.activity.stockinfo.search;


import tech.boshu.changjiangshidai.bean.api.StoreRequest;
import tech.boshu.changjiangshidai.bean.result.ResponseSearchCondition;
import tech.boshu.changjiangshidai.libs.activity.BaseInteractorImpl;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockSearchInteractorImpl.java
 */


public class StockSearchInteractorImpl extends BaseInteractorImpl implements
        IStockSearchInteractor {

    @Override
    public void querySearchCondition(ResponseCallback<ResponseSearchCondition> callback) {
        StoreRequest.querySearchCondition(callback);
    }

}