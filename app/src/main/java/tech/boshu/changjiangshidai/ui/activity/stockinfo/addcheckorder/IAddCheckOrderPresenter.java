package tech.boshu.changjiangshidai.ui.activity.stockinfo.addcheckorder;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IAddCheckOrderPresenter.java
 */


public interface IAddCheckOrderPresenter extends IBasePresenter {


    void chooseStock();

    void chooseProduct();

    void addCheckOrder();
}