package tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock;

import tech.boshu.changjiangshidai.bean.api.StoreRequest;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)ProductStockInteractorImpl.java
 */


public class ProductStockInteractorImpl implements IProductStockInteractor {

    @Override
    public void queryProductStock(int productId,
                                  int zeroNumStatus,
                                  ResponseCallback<ResponseProductStockInfo> callback) {
        RequestProductParam params = new RequestProductParam();
        params.productId = productId;
        params.zeroNumStatus = zeroNumStatus;
        StoreRequest.queryProductStore(params, callback);
    }
}