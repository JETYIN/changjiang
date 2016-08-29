package tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatehistory;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockAllocateHistoryPresenter.java
 */


public class StockAllocateHistoryPresenter extends BasePresenter implements IStockAllocateHistoryPresenter {

    private IStockAllocateHistoryView istockAllocateHistoryView;
    private IStockAllocateHistoryInteractor istockAllocateHistoryInteractor;

    public StockAllocateHistoryPresenter(IStockAllocateHistoryView istockAllocateHistoryView) {
        super(istockAllocateHistoryView);
        this.istockAllocateHistoryView = istockAllocateHistoryView;
        this.istockAllocateHistoryInteractor = new StockAllocateHistoryInteractorImpl();
    }


    @Override
    public void add() {
        istockAllocateHistoryView.add();
    }

    @Override
    public void gotoSearch() {
        istockAllocateHistoryView.gotoSearch();
    }
}