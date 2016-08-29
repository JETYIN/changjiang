package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory;

import java.util.List;

import tech.boshu.changjiangshidai.bean.result.CheckOrderHistory;
import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)CheckOrderHistoryPresenter.java
 */


public class CheckOrderHistoryPresenter extends BasePresenter implements
        ICheckOrderHistoryPresenter {

    private int storeId = -1;
    private String orderId = null;
    private String beginTime = null;
    private String endTime = null;
    private List<CheckOrderHistory> histories;

    private ICheckOrderHistoryView icheckOrderHistoryView;
    private ICheckOrderHistoryInteractor icheckOrderHistoryInteractor;


    public CheckOrderHistoryPresenter(ICheckOrderHistoryView icheckOrderHistoryView) {
        super(icheckOrderHistoryView);
        this.icheckOrderHistoryView = icheckOrderHistoryView;
        this.icheckOrderHistoryInteractor = new CheckOrderHistoryInteractorImpl();
    }


    @Override
    public void setSearchCondotion(int storeId, String orderId, String beginTime, String endTime) {
        this.storeId = storeId;
        this.orderId = orderId;
        this.beginTime = beginTime;
        this.endTime = endTime;
    }

    @Override
    public void setHistoryList() {
        icheckOrderHistoryView.showDialog();
        icheckOrderHistoryInteractor.queryHistoryList(
                storeId,
                orderId,
                beginTime,
                endTime,
                new ResponseCallback<ResponseCheckOrderHistory>() {
                    @Override
                    public void onRequestSuccess(ResponseCheckOrderHistory result) {
                        icheckOrderHistoryView.hideDialog();
                        histories = result.data.checkList;
                        icheckOrderHistoryView.setHistoryList(histories);
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        icheckOrderHistoryView.hideDialog();
                        icheckOrderHistoryView.showToast(error.message);
                    }
                });
    }

    @Override
    public void clickHistory(int position) {
        CheckOrderHistory history = histories.get(position);
        if (history.status == 0) {
            //草稿
            icheckOrderHistoryView.naviToCheckEdit(history.billNo);
        } else if (history.status == 1) {
            //已盘点，转跳盘点详情
            icheckOrderHistoryView.naviToCheckDetail(history.billNo);
        }
    }
}