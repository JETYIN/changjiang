package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.StoreBill;
import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockFlowResultPresenter.java
 */


public class StockFlowResultPresenter extends BasePresenter implements IStockFlowResultPresenter {
    private IStockFlowResultView istockFlowResultView;
    private IStockFlowResultInteractor istockFlowResultInteractor;

    private String storeName;
    private int storeId;
    private String productName;
    private int productId;
    private String startDate;
    private String endDate;
    private List<StoreBill> storeBills;

    public StockFlowResultPresenter(IStockFlowResultView istockFlowResultView) {
        super(istockFlowResultView);
        this.istockFlowResultView = istockFlowResultView;
        this.istockFlowResultInteractor = new StockFlowResultInteractorImpl();
    }


    @Override
    public void initPresenter(String storeName,
                              int storeId,
                              String productName,
                              int productId,
                              String startDate,
                              String endDate) {
        this.storeName = storeName;
        this.storeId = storeId;
        this.productName = productName;
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;

    }

    @Override
    public void setSearchInfo() {
        istockFlowResultView.setSearchCondition(storeName, productName, startDate + "至" + endDate);
    }

    @Override
    public void queryStockFlow() {
        istockFlowResultView.showDialog();
        istockFlowResultInteractor.queryFlowResult(
                this.storeId,
                this.productId,
                this.startDate,
                this.endDate,
                new ResponseCallback<ResponseStockFlow>() {
                    @Override
                    public void onRequestSuccess(ResponseStockFlow result) {
                        istockFlowResultView.hideDialog();
                        storeBills = result.data.storeBillList;
                        istockFlowResultView.setStcokFlowList(storeBills);
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        istockFlowResultView.hideDialog();
                        istockFlowResultView.showToast(error.message);
                    }
                });
    }

    @Override
    public void toCheckDetail(int position) {
        istockFlowResultView.naviToCheckDetail(storeBills.get(position).originalNo);
    }


}