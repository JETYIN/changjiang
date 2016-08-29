package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ICheckDetailPresenter.java
 */


public interface ICheckDetailPresenter extends IBasePresenter {
    void setOrderId(String orderId);

    void setCheckDetail();
}