package tech.boshu.changjiangshidai.ui.activity.stockinfo.flowsearchresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.StockFlowResultAdapter;
import tech.boshu.changjiangshidai.bean.mode.StoreBill;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail.CheckDetailActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)StockFlowResultActivity.java
 */


public class StockFlowResultActivity extends BaseActivity implements
        IStockFlowResultView,
        AdapterView.OnItemClickListener {

    private TextView stockTextView;
    private TextView productTextView;
    private TextView dateTextView;
    private ListView listView;

    private StockFlowResultAdapter adapter;
    private StockFlowResultPresenter stockFlowResultPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_stock_flow_result;
    }

    @Override
    protected void setPresenter() {
        stockFlowResultPresenter = new StockFlowResultPresenter(this);
        presenter = stockFlowResultPresenter;
        Intent intent = getIntent();
        stockFlowResultPresenter.initPresenter(
                intent.getStringExtra("storeName"),
                intent.getIntExtra("storeId", -1),
                intent.getStringExtra("productName"),
                intent.getIntExtra("productId", -1),
                intent.getStringExtra("startDate"),
                intent.getStringExtra("endDate"));
    }

    public void initView() {
        stockTextView = (TextView) findViewById(R.id.stock);
        productTextView = (TextView) findViewById(R.id.product);
        dateTextView = (TextView) findViewById(R.id.date);
        listView = (ListView) findViewById(R.id.list);
        adapter = new StockFlowResultAdapter(mContext, null);
        listView.setAdapter(adapter);

        initTitle("库存流水");
        stockFlowResultPresenter.setSearchInfo();
        stockFlowResultPresenter.queryStockFlow();

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        stockFlowResultPresenter.toCheckDetail(position);
    }

    @Override
    public void setSearchCondition(String storeName, String productName, String date) {
        stockTextView.setText(storeName);
        productTextView.setText(productName);
        dateTextView.setText(date);
    }

    @Override
    public void setStcokFlowList(List<StoreBill> storeBills) {
        adapter.setData(storeBills);
    }

    @Override
    public void naviToCheckDetail(String orderId) {
        Intent intent = new Intent(mContext, CheckDetailActivity.class);
        intent.putExtra("orderId", orderId);
        startActivity(intent);
    }

}