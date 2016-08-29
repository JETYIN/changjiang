package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkordersearch;


import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ICheckOrderSearchView.java
 */
public interface ICheckOrderSearchView extends IBaseView {
    void clearOption();

    void createStoreDialog(List<Store> stores);

    void createTimeDialog(int type, int year, int month, int day);

    void setStoreName(String name);

    void setStartDate(String time);

    void setEndDate(String time);

    void naviToCheckHistory(int storeId,
                            String orderId,
                            String startDate,
                            String endDate);
}