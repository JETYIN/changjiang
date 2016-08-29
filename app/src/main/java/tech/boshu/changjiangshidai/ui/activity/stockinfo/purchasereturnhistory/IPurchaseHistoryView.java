package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasereturnhistory;


import java.util.List;

import tech.boshu.changjiangshidai.libs.activity.IBaseView;
import tech.boshu.changjiangshidai.libs.bean.Pagination;

/**
 * @(#)IPurchaseHistoryView.java
 *
 *
 * @author allipper
 * @version 1.00 2016/1/6
 */
public interface IPurchaseHistoryView extends IBaseView{
    


    void bindDatas(List<PurchaseHistoryPrOrder> datas);

    void showNoDatas();

    Pagination getPagination();

    void setPagination(Pagination param);

    void stopRefresh();
}