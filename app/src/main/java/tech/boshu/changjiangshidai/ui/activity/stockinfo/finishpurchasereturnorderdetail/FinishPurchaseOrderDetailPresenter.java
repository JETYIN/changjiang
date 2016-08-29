package tech.boshu.changjiangshidai.ui.activity.stockinfo.finishpurchasereturnorderdetail;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)FinishPurchaseOrderDetailPresenter.java
 */


public class FinishPurchaseOrderDetailPresenter extends BasePresenter implements IFinishPurchaseOrderDetailPresenter {

    private IFinishPurchaseOrderDetailView ifinishPurchaseOrderDetailView;
    private IFinishPurchaseOrderDetailInteractor ifinishPurchaseOrderDetailInteractor;


    public FinishPurchaseOrderDetailPresenter(IFinishPurchaseOrderDetailView ifinishPurchaseOrderDetailView) {
        super(ifinishPurchaseOrderDetailView,new FinishPurchaseOrderDetailInteractorImpl());
        this.ifinishPurchaseOrderDetailView = ifinishPurchaseOrderDetailView;
        this.ifinishPurchaseOrderDetailInteractor = (IFinishPurchaseOrderDetailInteractor) iBaseInteractor;
    }


    public void setView() {
        ifinishPurchaseOrderDetailView.setView();
    }

    public void initView() {
        ifinishPurchaseOrderDetailView.initView();
    }



}