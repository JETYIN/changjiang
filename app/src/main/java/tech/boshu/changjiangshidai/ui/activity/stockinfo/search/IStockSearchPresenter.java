package tech.boshu.changjiangshidai.ui.activity.stockinfo.search;

import tech.boshu.changjiangshidai.libs.activity.IBasePresenter;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockSearchPresenter.java
 */


public interface IStockSearchPresenter extends IBasePresenter {

    void setSearchData();

    void clearOptions();

    void createStoreDialog();

    void createCatalogDialog();

    void createBrandDialog();

    void choiceDialog(int type, int position);

    void search(String minNumString, String maxNumString);
}