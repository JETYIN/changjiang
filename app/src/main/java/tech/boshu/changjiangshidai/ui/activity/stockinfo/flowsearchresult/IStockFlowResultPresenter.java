package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockFlowResultPresenter.java
 */


public interface IStockFlowResultPresenter extends IBasePresenter {

    void initPresenter(String storeName,
                       int storeId,
                       String productName,
                       int productId,
                       String startDate,
                       String endDate);

    void setSearchInfo();

    void queryStockFlow();

    void toCheckDetail(int position);
}