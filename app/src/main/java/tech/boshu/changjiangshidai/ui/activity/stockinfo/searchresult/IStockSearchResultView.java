package tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult;


import java.util.List;

import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockSearchResultView.java
 */
public interface IStockSearchResultView extends IBaseView {

    void setStoreStatus(String storeNum,
                        String storeAllNum,
                        String storeAllMoney,
                        String storeAllWarn);

    void setProductList(List<ModelStockProduct> productList);

    void naviToProductStock(int productId);

}