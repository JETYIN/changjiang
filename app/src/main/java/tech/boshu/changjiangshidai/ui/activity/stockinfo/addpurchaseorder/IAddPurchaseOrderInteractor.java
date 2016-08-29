package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder;

import java.util.List;

import tech.boshu.changjiangshidai.libs.activity.IBaseInteractor;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IAddPurchaseOrderInteractor.java
 */


public interface IAddPurchaseOrderInteractor extends IBaseInteractor {


    List<ResponseProviderListEntity> getSupplierList();

    List<ReponseStroeListEntity> getStockList();

    List<ReponseSetAccountListEntity> getSetAccountList();

    void setParam(AddPurchaseOrderParam param);

    AddPurchaseOrderParam getParam();

    void queryPurchaseOrderIfor(AddPurchaseOrderParam param, AddPurchaseOrderInterface addPurchaseOrderInterface);

    void add(AddPurchaseOrderInterface addPurchaseOrderInterface);

    void purchase(AddPurchaseOrderInterface addPurchaseOrderInterface);

    void cancel(AddPurchaseOrderInterface addPurchaseOrderInterface);

    void setPrepareData(List<ResponseProviderListEntity> list1,List<ReponseStroeListEntity> list2,
                        List<ReponseSetAccountListEntity> list3);
}