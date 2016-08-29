package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchasereturnorder;

/**
 * Created by allipper on 16/1/24.
 */
public interface AddPurchaseOrderInterface {

    void onQuerySuccess();
    void onAddSuccess();
    void onPurchaseSuccess();
    void onCancelSuccess();
    void onFail(String message);
}
