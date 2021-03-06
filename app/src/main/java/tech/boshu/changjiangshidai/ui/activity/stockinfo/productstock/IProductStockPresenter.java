package tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IProductStockPresenter.java
 */


public interface IProductStockPresenter extends IBasePresenter {
    void setProductId(int productId);

    void setZeroNumStatus(int zeroNumStatus);

    void setProductStock();
}