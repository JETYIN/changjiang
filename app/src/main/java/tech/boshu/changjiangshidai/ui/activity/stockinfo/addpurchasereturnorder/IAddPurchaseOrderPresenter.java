package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchasereturnorder;

import java.util.List;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IAddPurchaseOrderPresenter.java
 */


public interface IAddPurchaseOrderPresenter extends IBasePresenter {


    void selectSupplier();

    void selectStock();

    void selectPayWay();

    void scanProduct();

    void addProduct();

    void selectDate();

    void setParam(AddPurchaseOrderParam param);

    void queryPurchaseOrderInfor(AddPurchaseOrderParam param);

    void add();

    void cancel();

    void purchase();

    void setPrepareData(List<ResponseProviderListEntity> list1, List<ReponseStroeListEntity> list2,
                        List<ReponseSetAccountListEntity> list3);
}