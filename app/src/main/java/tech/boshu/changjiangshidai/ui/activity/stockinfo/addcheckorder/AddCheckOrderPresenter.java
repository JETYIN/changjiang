package tech.boshu.changjiangshidai.ui.activity.stockinfo.addcheckorder;

import android.app.Activity;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)AddCheckOrderPresenter.java
 */


public class AddCheckOrderPresenter extends BasePresenter implements IAddCheckOrderPresenter {

    private IAddCheckOrderView iaddCheckOrderView;
    private IAddCheckOrderInteractor iaddCheckOrderInteractor;


    public AddCheckOrderPresenter(IAddCheckOrderView iaddCheckOrderView) {
        super(iaddCheckOrderView);
        this.iaddCheckOrderView = iaddCheckOrderView;
        this.iaddCheckOrderInteractor = new AddCheckOrderInteractorImpl();
    }


    public void chooseStock() {
        iaddCheckOrderInteractor.chooseStock();
    }

    public void chooseProduct() {
        iaddCheckOrderInteractor.chooseProduct();
    }

    public void addCheckOrder() {
        iaddCheckOrderInteractor.addCheckOrder();
    }
}