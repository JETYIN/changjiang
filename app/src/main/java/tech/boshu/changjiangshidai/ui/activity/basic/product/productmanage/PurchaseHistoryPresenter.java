package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.bean.Pagination;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)PurchaseHistoryPresenter.java
 */


public class PurchaseHistoryPresenter extends BasePresenter implements IProductPresenter, CommonResponseCallback<ResponseProductManage> {

    private IProductHistoryView ipurchaseHistoryView;
    private IProductInteractor ipurchaseHistoryInteractor;

    public PurchaseHistoryPresenter(IProductHistoryView ipurchaseHistoryView) {
        super(ipurchaseHistoryView, new ProductInteractorImpl());
        this.ipurchaseHistoryView = ipurchaseHistoryView;
        this.ipurchaseHistoryInteractor = (IProductInteractor) iBaseInteractor;
    }

    public void filterByDefault() {
        ipurchaseHistoryInteractor.filterByDefault();

    }

    public void filterByCount() {
        ipurchaseHistoryInteractor.filterByCount();
        ipurchaseHistoryInteractor.setPagination(new Pagination());
    }

    public void filterByDraft() {
        ipurchaseHistoryInteractor.filterByDraft();
        ipurchaseHistoryInteractor.setPagination(new Pagination());
    }

    public void filterByCancel() {
        ipurchaseHistoryInteractor.filterByCancel();
        ipurchaseHistoryInteractor.setPagination(new Pagination());
    }


    @Override
    public void onRequestSuccess(ResponseProductManage result) {
        if (result.data.pdList != null && result.data.pdList.size() > 0) {
            ipurchaseHistoryView.bindDatas(result.data.pdList);
        } else {
            ipurchaseHistoryView.showNoDatas();
        }
        Pagination pg = ipurchaseHistoryView.getPagination();
        pg.pageNo = result.data.pageNo;
        pg.pageCount = result.data.pageCount;
        pg.pageSize = result.data.pageSize;
        pg.totalCount = result.data.totalCount;
        ipurchaseHistoryView.setPagination(pg);
        ipurchaseHistoryView.hideDialog();
    }

    @Override
    public void onFailed(ErrorBase error) {
        ipurchaseHistoryView.showToast(error.message);
        ipurchaseHistoryView.stopRefresh();
    }


    public void getDatas() {
        ipurchaseHistoryView.showDialog();
        ipurchaseHistoryInteractor.setOnSueccessCallback(this);
        ipurchaseHistoryInteractor.setPagination(ipurchaseHistoryView.getPagination());
        super.getDatas();
    }

    public void setParams(ProductRequestParam purchaseRequestParam) {
        ipurchaseHistoryInteractor.setParams(purchaseRequestParam);
    }
}