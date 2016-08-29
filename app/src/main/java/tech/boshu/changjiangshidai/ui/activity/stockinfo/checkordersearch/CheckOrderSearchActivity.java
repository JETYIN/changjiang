package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkordersearch;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)CheckOrderSearchActivity.java
 */


public class CheckOrderSearchActivity extends BaseActivity implements ICheckOrderSearchView {
    private TextView tvClear;
    private TextView chooseStockTextView;
    private EditText orderIdEditText;
    private TextView chooseStartTextView;
    private TextView chooseEndTextView;
    private Button searchButton;

    private CheckOrderSearchPresenter checkOrderSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_check_order_search;
    }

    @Override
    protected void setPresenter() {
        checkOrderSearchPresenter = new CheckOrderSearchPresenter(this);
        presenter = checkOrderSearchPresenter;
    }

    public void initView() {
        tvClear = (TextView) findViewById(R.id.right);
        chooseStockTextView = (TextView) findViewById(R.id.chooseStock);
        orderIdEditText = (EditText) findViewById(R.id.orderId);
        chooseStartTextView = (TextView) findViewById(R.id.chooseStart);
        chooseEndTextView = (TextView) findViewById(R.id.chooseEnd);
        searchButton = (Button) findViewById(R.id.search);
        initTitle("查询");
        tvClear.setText("清空");

        tvClear.setOnClickListener(this);
        chooseStockTextView.setOnClickListener(this);
        chooseStartTextView.setOnClickListener(this);
        chooseEndTextView.setOnClickListener(this);
        searchButton.setOnClickListener(this);

        checkOrderSearchPresenter.setSearchData();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
            checkOrderSearchPresenter.clearOption();
        } else if (id == R.id.chooseStock) {
            checkOrderSearchPresenter.createStoreDialog();
        } else if (id == R.id.chooseStart) {
            checkOrderSearchPresenter.createTimeDialog(CheckOrderSearchPresenter.TIME_START);
        } else if (id == R.id.chooseEnd) {
            checkOrderSearchPresenter.createTimeDialog(CheckOrderSearchPresenter.TIME_END);
        } else if (id == R.id.search) {
            checkOrderSearchPresenter.search();
        }
    }

    @Override
    public void clearOption() {
        chooseStockTextView.setText("");
        orderIdEditText.setText("");
        chooseStartTextView.setText("");
        chooseEndTextView.setText("");
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
    public void createTimeDialog(final int type, int year, int month, int day) {
        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                checkOrderSearchPresenter.choiceTimeDialog(type, year, month, day);
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
    public void naviToCheckHistory(int storeId,
                                   String orderId,
                                   String startDate,
                                   String endDate) {
        Intent intent = new Intent();
        intent.putExtra("storeId", storeId);
        intent.putExtra("orderId", orderIdEditText.getEditableText().toString());
        intent.putExtra("startDate", startDate);
        intent.putExtra("endDate", endDate);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void createDialog(List<String> items) {
        String[] strings = new String[items.size()];
        items.toArray(strings);
        new AlertDialog.Builder(mContext).setItems(strings, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int position) {
                checkOrderSearchPresenter.choiceDialog(position);
            }
        }).show();
    }

}