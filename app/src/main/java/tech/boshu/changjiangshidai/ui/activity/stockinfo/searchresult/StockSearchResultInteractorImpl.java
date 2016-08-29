package tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult;

import tech.boshu.changjiangshidai.bean.api.StoreRequest;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockSearchResultInteractorImpl.java
 */


public class StockSearchResultInteractorImpl implements
        IStockSearchResultInteractor {


    @Override
    public void sortProduct(int code) {

    }

    @Override
    public void queryStoreInforByNet(int storeId,
                                     int catalogId,
                                     int brandId,
                                     int minNum,
                                     int maxNum,
                                     ResponseCallback<ResponseStoreInfo> callback) {
        RequestStoreInfoParam param = new RequestStoreInfoParam();
        param.companyId = "2222";
        param.storeId = storeId;
        param.catalogId = catalogId;
        param.brandId = brandId;
        param.minNum = minNum;
        param.maxNum = maxNum;
        StoreRequest.queryStore(param, callback);
    }
}