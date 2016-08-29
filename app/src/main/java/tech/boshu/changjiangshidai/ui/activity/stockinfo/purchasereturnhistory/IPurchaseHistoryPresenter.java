package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasereturnhistory;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IPurchaseHistoryPresenter.java
 */


public interface IPurchaseHistoryPresenter extends IBasePresenter {

    void filterByDefault();

    void filterByCount();

    void filterByDraft();

    void filterByCancel();

}