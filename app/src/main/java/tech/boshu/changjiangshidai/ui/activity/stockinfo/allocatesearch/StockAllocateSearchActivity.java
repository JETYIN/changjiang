package tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatesearch;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)StockAllocateSearchActivity.java
 */


public class StockAllocateSearchActivity extends BaseActivity implements IStockAllocateSearchView {

    private TextView inStockTextView;
    private TextView outStockTextView;
    private EditText orderIdEditText;
    private TextView chooseStartTextView;
    private TextView chooseEndTextView;
    private Button searchButton;


    public void initView() {
        inStockTextView = (TextView) findViewById(R.id.inStock);
        outStockTextView = (TextView) findViewById(R.id.outStock);
        orderIdEditText = (EditText) findViewById(R.id.orderId);
        chooseStartTextView = (TextView) findViewById(R.id.chooseStart);
        chooseEndTextView = (TextView) findViewById(R.id.chooseEnd);
        searchButton = (Button) findViewById(R.id.search);

    }

    private StockAllocateSearchPresenter stockAllocateSearchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTitle("查询筛选");
        stockAllocateSearchPresenter.setText(R.id.right, "");
        setOnclickListener(R.id.search, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stockAllocateSearchPresenter.search();
            }
        });
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_stock_allocate_search;
    }

    @Override
    protected void setPresenter() {
        stockAllocateSearchPresenter = (StockAllocateSearchPresenter) (presenter =
                new StockAllocateSearchPresenter(StockAllocateSearchActivity.this));
    }
}