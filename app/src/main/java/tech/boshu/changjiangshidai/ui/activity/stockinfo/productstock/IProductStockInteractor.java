package tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock;

import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IProductStockInteractor.java
 */


public interface IProductStockInteractor {
    void queryProductStock(int productId,
                           int zeroNumStatus,
                           ResponseCallback<ResponseProductStockInfo> callback);
}