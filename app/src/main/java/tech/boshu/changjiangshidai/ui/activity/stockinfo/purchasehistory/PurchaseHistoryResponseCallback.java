package tech.boshu.changjiangshidai.ui.activity.stockinfo.purchasehistory;

import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * Created by allipper on 16/1/18.
 */
public interface PurchaseHistoryResponseCallback {
    void onRequestSuccess(ResponsePurchaseHistory result);

    void onFailed(ErrorBase error);
}
