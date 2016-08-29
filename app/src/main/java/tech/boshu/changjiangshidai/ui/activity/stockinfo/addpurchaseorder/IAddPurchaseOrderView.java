package tech.boshu.changjiangshidai.ui.activity.stockinfo.addpurchaseorder;


import java.util.List;

import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IAddPurchaseOrderView.java
 */
public interface IAddPurchaseOrderView extends IBaseView {


    void selectSupplier(List<ResponseProviderListEntity> supplierList) ;

    void selectStock(List<ReponseStroeListEntity> stockList) ;

    void selectPayWay(List<ReponseSetAccountListEntity> setAccountList) ;

    void scanProduct() ;

    void addProduct() ;

    void selectDate() ;

    void setView() ;

    void setParam(AddPurchaseOrderParam param);

}