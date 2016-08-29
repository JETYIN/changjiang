package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail;

import tech.boshu.changjiangshidai.bean.result.ResponseCheckDetail;
import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)CheckDetailPresenter.java
 */


public class CheckDetailPresenter extends BasePresenter implements ICheckDetailPresenter {
    private ICheckDetailView icheckDetailView;
    private ICheckDetailInteractor icheckDetailInteractor;

    private String orderId;

    public CheckDetailPresenter(ICheckDetailView icheckDetailView) {
        super(icheckDetailView);
        this.icheckDetailView = icheckDetailView;
        this.icheckDetailInteractor = new CheckDetailInteractorImpl();
    }


    @Override
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @Override
    public void setCheckDetail() {
        icheckDetailView.showDialog();
        icheckDetailInteractor.queryCheckDetail(
                orderId,
                new ResponseCallback<ResponseCheckDetail>() {

                    @Override
                    public void onRequestSuccess(ResponseCheckDetail result) {
                        icheckDetailView.hideDialog();
                        icheckDetailView.setCheckDetailInfo(result.data.order);
                        icheckDetailView.setCheckDetailList(result.data.orderDetailDtoList);
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        icheckDetailView.hideDialog();
                        icheckDetailView.showToast(error.message);
                    }
                });
    }
}