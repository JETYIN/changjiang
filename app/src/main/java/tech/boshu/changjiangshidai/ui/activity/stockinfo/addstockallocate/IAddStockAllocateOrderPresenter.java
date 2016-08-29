package tech.boshu.changjiangshidai.ui.activity.stockinfo.addstockallocate;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IAddStockAllocateOrderPresenter.java
 */


public interface IAddStockAllocateOrderPresenter extends IBasePresenter {


    void draft();

    void sale();

    void chooseOutStock();

    void chooseInStock();

    void chooseProduct();

    void addToAllocateOrder();

    void edit();
}