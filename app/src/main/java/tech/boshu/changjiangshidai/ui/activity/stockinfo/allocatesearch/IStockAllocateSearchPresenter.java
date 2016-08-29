package tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatesearch;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockAllocateSearchPresenter.java
 */


public interface IStockAllocateSearchPresenter extends IBasePresenter {

    void chooseInStock();

    void chooseOutStock();

    void chooseStart();

    void chooseEnd();

    void search();
}