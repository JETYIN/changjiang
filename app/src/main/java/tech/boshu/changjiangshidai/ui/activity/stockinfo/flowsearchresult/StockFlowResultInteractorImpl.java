package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult;

import tech.boshu.changjiangshidai.bean.api.StoreRequest;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockFlowResultInteractorImpl.java
 */


public class StockFlowResultInteractorImpl implements IStockFlowResultInteractor {

    @Override
    public void queryFlowResult(int storeId,
                                int productId,
                                String beginTime,
                                String endTime,
                                ResponseCallback<ResponseStockFlow> callback) {
        RequestStockFlowParam param = new RequestStockFlowParam();
        param.storeId = storeId;
        param.productId = productId;
        param.beginTime = beginTime;
        param.endTime = endTime;
        StoreRequest.queryStoreFlow(param, callback);
    }

}