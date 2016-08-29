package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasereturnhistory;

import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;
import tech.boshu.changjiangshidai.libs.bean.Pagination;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IPurchaseHistoryInteractor.java
 */


public interface IPurchaseHistoryInteractor extends IBaseInteractor {

    void filterByDefault();

    void filterByCount();

    void filterByDraft();

    void filterByCancel();

    void setPagination(Pagination param);

    void setOnSueccessCallback(PurchaseHistoryResponseCallback callback);

    void setParams(PurchaseRequestParam params);

}