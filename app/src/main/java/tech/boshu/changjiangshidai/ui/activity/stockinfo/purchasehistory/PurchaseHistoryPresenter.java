package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.bean.Pagination;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)PurchaseHistoryPresenter.java
 */

/**实现状态接口，网络请求返回数据接口**/
public class PurchaseHistoryPresenter extends BasePresenter implements IPurchaseHistoryPresenter, PurchaseHistoryResponseCallback {

    private IPurchaseHistoryView ipurchaseHistoryView;
    private IPurchaseHistoryInteractor ipurchaseHistoryInteractor;

    public PurchaseHistoryPresenter(IPurchaseHistoryView ipurchaseHistoryView) {
        super(ipurchaseHistoryView, new PurchaseHistoryInteractorImpl());
        this.ipurchaseHistoryView = ipurchaseHistoryView;
        this.ipurchaseHistoryInteractor = (IPurchaseHistoryInteractor) iBaseInteractor;
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
    public void onRequestSuccess(ResponsePurchaseHistory result) {
        if (result.data.prOrderList != null && result.data.prOrderList.size() > 0) {
            ipurchaseHistoryView.bindDatas(result.data.prOrderList);
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

    public void setParams(PurchaseRequestParam purchaseRequestParam) {
        ipurchaseHistoryInteractor.setParams(purchaseRequestParam);
    }
}