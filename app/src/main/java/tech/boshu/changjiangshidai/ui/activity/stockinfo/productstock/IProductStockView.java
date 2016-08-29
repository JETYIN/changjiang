package tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock;


import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IProductStockView.java
 */
public interface IProductStockView extends IBaseView {

    void setProductStockInfo(String name,
                             double purchasePrice,
                             double wholesalePrice,
                             double retailPrice,
                             String brand,
                             String catalog,
                             int mergerNum);
}