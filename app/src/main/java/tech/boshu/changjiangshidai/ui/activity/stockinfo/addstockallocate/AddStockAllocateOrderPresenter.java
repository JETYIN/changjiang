package tech.boshu.changjiangshidai.ui.activity.stockinfo.addstockallocate;

import android.app.Activity;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)AddStockAllocateOrderPresenter.java
 */


public class AddStockAllocateOrderPresenter extends BasePresenter implements IAddStockAllocateOrderPresenter {

    private IAddStockAllocateOrderView iaddStockAllocateOrderView;
    private IAddStockAllocateOrderInteractor iaddStockAllocateOrderInteractor;



    public AddStockAllocateOrderPresenter(IAddStockAllocateOrderView iaddStockAllocateOrderView){   super(iaddStockAllocateOrderView);
        this.iaddStockAllocateOrderView = iaddStockAllocateOrderView;
        this.iaddStockAllocateOrderInteractor = new AddStockAllocateOrderInteractorImpl();
    }



    public void draft() {
        iaddStockAllocateOrderInteractor.draft();
    }

    public void sale() {
        iaddStockAllocateOrderInteractor.sale();
    }

    public void chooseOutStock() {
        iaddStockAllocateOrderInteractor.chooseOutStock();
    }

    public void chooseInStock() {
        iaddStockAllocateOrderInteractor.chooseInStock();
    }

    public void chooseProduct() {
        iaddStockAllocateOrderInteractor.chooseProduct();
    }

    public void addToAllocateOrder() {
        iaddStockAllocateOrderInteractor.addToAllocateOrder();
    }

    public void edit() {
        iaddStockAllocateOrderInteractor.edit();
    }
}