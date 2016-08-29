package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory;


import java.util.List;

import tech.boshu.changjiangshidai.bean.result.CheckOrderHistory;
import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ICheckOrderHistoryView.java
 */
public interface ICheckOrderHistoryView extends IBaseView {

    void setHistoryList(List<CheckOrderHistory> checkList);

    void naviToCheckDetail(String orderId);

    void naviToCheckEdit(String orderId);

}