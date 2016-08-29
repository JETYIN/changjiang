package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult;


import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.StoreBill;
import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockFlowResultView.java
 */
public interface IStockFlowResultView extends IBaseView {

    void setSearchCondition(String storeName, String productName, String date);

    void setStcokFlowList(List<StoreBill> storeBills);

    void naviToCheckDetail(String orderId);
}