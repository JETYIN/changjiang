package tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatedetail;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockAllocateDetailPresenter.java
 */


public class StockAllocateDetailPresenter extends BasePresenter implements IStockAllocateDetailPresenter {

    private IStockAllocateDetailView istockAllocateDetailView;
    private IStockAllocateDetailInteractor istockAllocateDetailInteractor;

    public StockAllocateDetailPresenter(IStockAllocateDetailView istockAllocateDetailView) {
        super(istockAllocateDetailView);
        this.istockAllocateDetailView = istockAllocateDetailView;
        this.istockAllocateDetailInteractor = new StockAllocateDetailInteractorImpl();
    }


}