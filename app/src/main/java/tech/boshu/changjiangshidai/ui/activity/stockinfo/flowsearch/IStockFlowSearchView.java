package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearch;


import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockFlowSearchView.java
 */
public interface IStockFlowSearchView extends IBaseView {

    void createStoreDialog(List<Store> stores);

    void createTimeDialog(int type, int year, int month, int day);

    void setStoreName(String name);

    void setStartDate(String time);

    void setEndDate(String time);

    void naviToStockFlowResult(String storeName,
                               int storeId,
                               String productName,
                               int productId,
                               String startDate,
                               String endDate);

}