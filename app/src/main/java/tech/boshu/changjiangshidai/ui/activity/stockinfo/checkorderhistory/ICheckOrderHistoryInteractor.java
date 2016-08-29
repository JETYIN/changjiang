package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory;

import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ICheckOrderHistoryInteractor.java
 */


public interface ICheckOrderHistoryInteractor {

    void queryHistoryList(int storeId,
                          String orderId,
                          String beginTime,
                          String endTime,
                          ResponseCallback<ResponseCheckOrderHistory> callback);

}