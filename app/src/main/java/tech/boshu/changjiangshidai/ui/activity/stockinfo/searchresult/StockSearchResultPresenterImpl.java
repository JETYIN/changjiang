package tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult;

import java.util.List;

import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockSearchResultPresenterImpl.java
 */


public class StockSearchResultPresenterImpl extends BasePresenter implements
        IStockSearchResultPresenter {

    private IStockSearchResultView view;
    private StockSearchResultInteractorImpl interactor;
    private List<ModelStockProduct> productList;

    public StockSearchResultPresenterImpl(IStockSearchResultView view) {
        super(view);
        this.view = view;
        this.interactor = new StockSearchResultInteractorImpl();
    }


    @Override
    public void sortProductList(int code) {
    }

    @Override
    public void setStoreInfor(int storeId, int catalogId, int brandId, int minNum, int maxNum) {
        if (view != null) {
            view.showDialog();
        }
        interactor.queryStoreInforByNet(
                storeId,
                catalogId,
                brandId,
                minNum,
                maxNum,
                new ResponseCallback<ResponseStoreInfo>() {

                    @Override
                    public void onRequestSuccess(ResponseStoreInfo result) {
                        productList = result.data.productList;
                        if (view != null) {
                            view.hideDialog();
                            view.setProductList(productList);
                            view.setStoreStatus(String.valueOf(result.data.storeNum),
                                    String.valueOf(result.data.storeAllNum),
                                    "￥" + result.data.storeCost,
                                    String.valueOf(result.data.warningNum));
                        }
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        if (view != null) {
                            view.hideDialog();
                            view.showToast(error.message);
                        }
                    }
                });
    }

    @Override
    public void naviToProductStock(int position) {
        if (view != null) {
            view.naviToProductStock(productList.get(position).productId);
        }
    }

}