package tech.boshu.changjiangshidai.ui.activity.stockinfo.search;

import android.content.Intent;
import android.text.TextUtils;

import java.util.List;

import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.bean.result.Brand;
import tech.boshu.changjiangshidai.bean.result.Catalog;
import tech.boshu.changjiangshidai.bean.result.ResponseSearchCondition;
import tech.boshu.changjiangshidai.libs.activity.BasePresenter;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;

/**
 * @author
 * @version 1.00 2016/1/6
 * @(#)StockSearchPresenter.java
 */


public class StockSearchPresenter extends BasePresenter implements IStockSearchPresenter {

    private IStockSearchView istockSearchView;
    private StockSearchInteractorImpl istockSearchInteractor;

    private List<Store> stores;
    private List<Catalog> catalogs;
    private List<Brand> brands;

    private int storeId = -1;
    private int catalogId = -1;
    private int brandId = -1;


    public StockSearchPresenter(IStockSearchView istockSearchView) {
        super(istockSearchView);
        this.istockSearchView = istockSearchView;
        this.istockSearchInteractor = new StockSearchInteractorImpl();
    }

    public void clearOptions() {
        istockSearchView.clearOptions();
        storeId = -1;
        catalogId = -1;
        brandId = -1;
    }

    @Override
    public void createStoreDialog() {
        if (stores == null || stores.size() == 0) {
            return;
        }
        istockSearchView.createStoreDialog(stores);
    }

    @Override
    public void createCatalogDialog() {
        if (catalogs == null || catalogs.size() == 0) {
            return;
        }
        istockSearchView.createCatalogDialog(catalogs);
    }

    @Override
    public void createBrandDialog() {
        if (brands == null || brands.size() == 0) {
            return;
        }
        istockSearchView.createBrandDialog(brands);
    }

    @Override
    public void choiceDialog(int type, int position) {
        if (type == 0) {
            storeId = stores.get(position).id;
            istockSearchView.setStoreName(stores.get(position).name);
        } else if (type == 1) {
            catalogId = catalogs.get(position).id;
            istockSearchView.setCatalogName(catalogs.get(position).name);
        } else if (type == 2) {
            brandId = brands.get(position).id;
            istockSearchView.setBrandName(brands.get(position).name);
        }
    }


    @Override
    public void setSearchData() {
        istockSearchView.showDialog();
        istockSearchInteractor.querySearchCondition(
                new ResponseCallback<ResponseSearchCondition>() {

                    @Override
                    public void onRequestSuccess(ResponseSearchCondition result) {
                        istockSearchView.hideDialog();
                        stores = result.data.stroeList;
                        catalogs = result.data.catalogType;
                        brands = result.data.brandType;
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        istockSearchView.hideDialog();
                        istockSearchView.showToast(error.message);
                    }
                });
    }

    @Override
    public void search(String minNumString, String maxNumString) {
        if (!TextUtils.isEmpty(minNumString) && !TextUtils.isEmpty(maxNumString)) {
            int minNum = Integer.parseInt(minNumString);
            int maxNum = Integer.parseInt(maxNumString);
            if (minNum >= maxNum) {
                istockSearchView.showToast("最大库存必须大于最小库存");
                return;
            }
        }
        Intent intent = new Intent();
        intent.putExtra("storeId", storeId);
        intent.putExtra("catalogId", catalogId);
        intent.putExtra("brandId", brandId);
        intent.putExtra("minNum",
                TextUtils.isEmpty(minNumString) ? -1 : Integer.parseInt(minNumString));
        intent.putExtra("maxNum",
                TextUtils.isEmpty(maxNumString) ? -1 : Integer.parseInt(maxNumString));
        istockSearchView.naviToSearchResult(intent);
    }
}