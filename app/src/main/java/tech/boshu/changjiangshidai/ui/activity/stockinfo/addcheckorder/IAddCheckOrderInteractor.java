package tech.boshu.changjiangshidai.ui.activity.stockinfo.addcheckorder;

import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IAddCheckOrderInteractor.java
 */


public interface IAddCheckOrderInteractor extends IBaseInteractor {

    void chooseStock();

    void chooseProduct();

    void addCheckOrder();

}