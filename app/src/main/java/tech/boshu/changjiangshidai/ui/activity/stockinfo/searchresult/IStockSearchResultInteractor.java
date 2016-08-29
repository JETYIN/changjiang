package tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult;

import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockSearchResultInteractor.java
 */


public interface IStockSearchResultInteractor {


    void sortProduct(int code);

    void queryStoreInforByNet(int storeId,
                              int catalogId,
                              int brandId,
                              int minNum,
                              int maxNum,
                              ResponseCallback<ResponseStoreInfo> callback);

}