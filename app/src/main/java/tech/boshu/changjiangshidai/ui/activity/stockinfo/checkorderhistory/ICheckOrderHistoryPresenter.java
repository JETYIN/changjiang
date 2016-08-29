package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ICheckOrderHistoryPresenter.java
 */


public interface ICheckOrderHistoryPresenter extends IBasePresenter {

    void setSearchCondotion(int storeId, String orderId, String beginTime, String endTime);

    void setHistoryList();

    void clickHistory(int position);

}