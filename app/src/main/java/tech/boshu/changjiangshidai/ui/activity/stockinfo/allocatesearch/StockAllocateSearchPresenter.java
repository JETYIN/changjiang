package tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatesearch;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockAllocateSearchPresenter.java
 */


public class StockAllocateSearchPresenter extends BasePresenter implements IStockAllocateSearchPresenter {

    private IStockAllocateSearchView istockAllocateSearchView;
    private IStockAllocateSearchInteractor istockAllocateSearchInteractor;


    public StockAllocateSearchPresenter(IStockAllocateSearchView istockAllocateSearchView) {
        super(istockAllocateSearchView);
        this.istockAllocateSearchView = istockAllocateSearchView;
        this.istockAllocateSearchInteractor = new StockAllocateSearchInteractorImpl();
    }


    public void chooseInStock() {
        istockAllocateSearchInteractor.chooseInStock();
    }

    public void chooseOutStock() {
        istockAllocateSearchInteractor.chooseOutStock();
    }

    public void chooseStart() {
        istockAllocateSearchInteractor.chooseStart();
    }

    public void chooseEnd() {
        istockAllocateSearchInteractor.chooseEnd();
    }

    public void search() {
        istockAllocateSearchInteractor.search();
    }
}