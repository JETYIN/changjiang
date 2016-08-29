package tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatesearch;

import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockAllocateSearchInteractor.java
 */


public interface IStockAllocateSearchInteractor extends IBaseInteractor {


    void chooseInStock();

    void chooseOutStock();

    void chooseStart();

    void chooseEnd();

    void search();

}