package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult;

import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockFlowResultInteractor.java
 */


public interface IStockFlowResultInteractor {

    void queryFlowResult(int storeId,
                         int productId,
                         String beginTime,
                         String endTime,
                         ResponseCallback<ResponseStockFlow> callback);
}