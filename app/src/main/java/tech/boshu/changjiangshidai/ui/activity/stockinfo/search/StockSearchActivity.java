package tech.boshu.changjiangshidai.ui.activity.stockinfo.search;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.bean.result.Brand;
import tech.boshu.changjiangshidai.bean.result.Catalog;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.DialogUtils;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)StockSearchActivity.java
 */


public class StockSearchActivity extends BaseActivity implements IStockSearchView {

    private TextView tvClear;
    private TextView chooseStockTextView;
    private TextView chooseCategoryTextView;
    private TextView chooseBrandTextView;
    private EditText chooseMinEditText;
    private EditText chooseMaxEditText;
    private Button searchButton;
    private Dialog dialog;

    private StockSearchPresenter stockSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_stock_search;
    }

    @Override
    protected void setPresenter() {
        stockSearchPresenter = new StockSearchPresenter(this);
        presenter = stockSearchPresenter;
    }

    public void initView() {
        tvClear = (TextView) findViewById(R.id.right);
        chooseStockTextView = (TextView) findViewById(R.id.chooseStock);
        chooseCategoryTextView = (TextView) findViewById(R.id.chooseCategory);
        chooseBrandTextView = (TextView) findViewById(R.id.chooseBrand);
        chooseMinEditText = (EditText) findViewById(R.id.chooseMin);
        chooseMaxEditText = (EditText) findViewById(R.id.chooseMax);
        searchButton = (Button) findViewById(R.id.search);
        dialog = DialogUtils.createLoadingDialog(mContext);

        initTitle("查询筛选");
        tvClear.setText("清空");
        tvClear.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        chooseStockTextView.setOnClickListener(this);
        chooseCategoryTextView.setOnClickListener(this);
        chooseBrandTextView.setOnClickListener(this);
        stockSearchPresenter.setSearchData();
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.right) {
            stockSearchPresenter.clearOptions();
        } else if (id == R.id.chooseStock) {
            stockSearchPresenter.createStoreDialog();
        } else if (id == R.id.chooseCategory) {
            stockSearchPresenter.createCatalogDialog();
        } else if (id == R.id.chooseBrand) {
            stockSearchPresenter.createBrandDialog();
        } else if (id == R.id.search) {
            stockSearchPresenter.search(chooseMinEditText.getEditableText().toString(),
                    chooseMaxEditText.getEditableText().toString());
        }
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        ToastUtils.show(this, msg);
    }

    @Override
    public void clearOptions() {
        chooseStockTextView.setText("");
        chooseCategoryTextView.setText("");
        chooseBrandTextView.setText("");
        chooseMinEditText.setText("");
        chooseMaxEditText.setText("");
    }

    @Override
    public void createStoreDialog(List<Store> stores) {
        List<String> items = new ArrayList<>();
        for (Store store : stores) {
            items.add(store.name);
        }
        createDialog(0, items);
    }

    @Override
    public void createCatalogDialog(List<Catalog> catalogs) {
        List<String> items = new ArrayList<>();
        for (Catalog catalog : catalogs) {
            items.add(catalog.name);
        }
        createDialog(1, items);
    }

    @Override
    public void createBrandDialog(List<Brand> brands) {
        List<String> items = new ArrayList<>();
        for (Brand brand : brands) {
            items.add(brand.name);
        }
        createDialog(2, items);
    }

    @Override
    public void naviToSearchResult(Intent intent) {
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void setStoreName(String name) {
        chooseStockTextView.setText(name);
    }

    @Override
    public void setCatalogName(String name) {
        chooseCategoryTextView.setText(name);
    }

    @Override
    public void setBrandName(String name) {
        chooseBrandTextView.setText(name);
    }

    private void createDialog(final int type, List<String> items) {
        String[] strings = new String[items.size()];
        items.toArray(strings);
        new AlertDialog.Builder(mContext).setItems(strings, new
                DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int position) {
                        stockSearchPresenter.choiceDialog(type, position);
                    }
                }).show();
    }
}