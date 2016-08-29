package tech.boshu.changjiangshidai.ui.activity.stockinfo.addstockallocate;

import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IAddStockAllocateOrderInteractor.java
 */


public interface IAddStockAllocateOrderInteractor extends IBaseInteractor {


    void draft();

    void sale();

    void chooseOutStock();

    void chooseInStock();

    void chooseProduct();

    void addToAllocateOrder();

    void edit();

}