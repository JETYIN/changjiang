package tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockSearchResultPresenter.java
 */


public interface IStockSearchResultPresenter {


    void sortProductList(int code);

    void setStoreInfor(int storeId, int catalogId, int brandId, int minNum, int maxNum);

    void naviToProductStock(int position);
}