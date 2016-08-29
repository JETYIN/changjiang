package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail;

import tech.boshu.changjiangshidai.bean.result.ResponseCheckDetail;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ICheckDetailInteractor.java
 */


public interface ICheckDetailInteractor {
    void queryCheckDetail(String orderId,ResponseCallback<ResponseCheckDetail> callback);

}