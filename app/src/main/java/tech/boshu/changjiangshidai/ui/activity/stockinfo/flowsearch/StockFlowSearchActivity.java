package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearch;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.bean.mode.Store;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.utils.DialogUtils;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult.StockFlowResultActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)StockFlowSearchActivity.java
 */


public class StockFlowSearchActivity extends BaseActivity implements IStockFlowSearchView {

    private TextView chooseStockTextView;
    private TextView chooseProductTextView;
    private EditText chooseAreaEditText;
    private EditText chooseMaxEditText;
    private TextView chooseStartTextView;
    private TextView chooseEndTextView;
    private Button searchButton;
    private Dialog dialog;

    private StockFlowSearchPresenter stockFlowSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_stock_flow_search;
    }

    @Override
    protected void setPresenter() {
        stockFlowSearchPresenter = new StockFlowSearchPresenter(this);
        presenter = stockFlowSearchPresenter;
    }

    public void initView() {
        chooseStockTextView = (TextView) findViewById(R.id.chooseStock);
        chooseProductTextView = (TextView) findViewById(R.id.chooseProduct);
        chooseAreaEditText = (EditText) findViewById(R.id.chooseArea);
        chooseMaxEditText = (EditText) findViewById(R.id.chooseMax);
        chooseStartTextView = (TextView) findViewById(R.id.chooseStart);
        chooseEndTextView = (TextView) findViewById(R.id.chooseEnd);
        searchButton = (Button) findViewById(R.id.search);
        dialog = DialogUtils.createLoadingDialog(mContext);

        initTitle("库存流水");
        chooseStockTextView.setOnClickListener(this);
        chooseProductTextView.setOnClickListener(this);
        chooseStartTextView.setOnClickListener(this);
        chooseEndTextView.setOnClickListener(this);
        searchButton.setOnClickListener(this);

        stockFlowSearchPresenter.setSearchData();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.search) {
            stockFlowSearchPresenter.search();
        } else if (id == R.id.chooseStock) {
            stockFlowSearchPresenter.createStoreDialog();
        } else if (id == R.id.chooseProduct) {

        } else if (id == R.id.chooseStart) {
            stockFlowSearchPresenter.createTimeDialog(StockFlowSearchPresenter.TIME_START);
        } else if (id == R.id.chooseEnd) {
            stockFlowSearchPresenter.createTimeDialog(StockFlowSearchPresenter.TIME_END);
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
    public void createStoreDialog(List<Store> stores) {
        List<String> items = new ArrayList<>();
        for (Store store : stores) {
            items.add(store.name);
        }
        createDialog(items);
    }

    @Override
    public void createTimeDialog(final int type, final int year, final int month, final int day) {
        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                stockFlowSearchPresenter.choiceTimeDialog(type, year, month, day);
            }
        }, year, month, day).show();
    }

    @Override
    public void setStoreName(String name) {
        chooseStockTextView.setText(name);
    }

    @Override
    public void setStartDate(String time) {
        chooseStartTextView.setText(time);
    }

    @Override
    public void setEndDate(String time) {
        chooseEndTextView.setText(time);
    }

    @Override
    public void naviToStockFlowResult(String storeName,
                                      int storeId,
                                      String productName,
                                      int productId,
                                      String startDate,
                                      String endDate) {
        Intent intent = new Intent(mContext, StockFlowResultActivity.class);
        intent.putExtra("storeName", storeName);
        intent.putExtra("storeId", storeId);
        intent.putExtra("productName", productName);
        intent.putExtra("productId", productId);
        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);
        startActivity(intent);
    }

    private void createDialog(List<String> items) {
        String[] strings = new String[items.size()];
        items.toArray(strings);
        new AlertDialog.Builder(mContext).setItems(strings, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                stockFlowSearchPresenter.choiceDialog(position);
            }
        }).show();
    }

}