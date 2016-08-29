package tech.boshu.changjiangshidai.ui.activity.stockinfo.search;


import android.content.Intent;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.bean.result.Brand;
import tech.boshu.changjiangshidai.bean.result.Catalog;
import tech.boshu.changjiangshidai.libs.activity.IBaseView;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)IStockSearchView.java
 */
public interface IStockSearchView extends IBaseView {

    void clearOptions();

    void createStoreDialog(List<Store> stores);

    void createCatalogDialog(List<Catalog> catalogs);

    void createBrandDialog(List<Brand> brands);

    void naviToSearchResult(Intent intent);

    void setStoreName(String name);

    void setCatalogName(String name);

    void setBrandName(String name);
}