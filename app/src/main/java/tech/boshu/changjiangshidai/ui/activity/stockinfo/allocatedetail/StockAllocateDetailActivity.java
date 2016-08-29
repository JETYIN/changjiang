package tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatedetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.StockAllocateDetailAdapter;
import tech.boshu.changjiangshidai.bean.StockAllocateDetailItem;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail.CheckDetailActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)StockAllocateDetailActivity.java
 */


public class StockAllocateDetailActivity extends BaseActivity implements IStockAllocateDetailView {

    private ImageView icon1ImageView;
    private TextView orderIdTextView;
    private ImageView icon2ImageView;
    private TextView makerTextView;
    private ImageView icon3ImageView;
    private TextView outStockTextView;
    private ImageView icon4ImageView;
    private TextView instockTextView;
    private ImageView icon_rightImageView;
    private TextView countTextView;
    private ListView listListView;

    private StockAllocateDetailAdapter adapter;
    private List<StockAllocateDetailItem> datas = new ArrayList<>();

    public void setView() {

        setText(R.id.orderId, "PD33333333");
        setText(R.id.maker, "徐莎");
        setText(R.id.outStock, "四楼仓库");
        setText(R.id.instock, "五楼仓库");
        setText(R.id.count, "4");
        for (int i = 0; i < 6; i++) {
            datas.add(new StockAllocateDetailItem());
        }
        adapter = new StockAllocateDetailAdapter(mContext, datas);
        listListView.setAdapter(adapter);
        listListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, CheckDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initView() {
        icon1ImageView = (ImageView) findViewById(R.id.icon1);
        orderIdTextView = (TextView) findViewById(R.id.orderId);
        icon2ImageView = (ImageView) findViewById(R.id.icon2);
        makerTextView = (TextView) findViewById(R.id.maker);
        icon3ImageView = (ImageView) findViewById(R.id.icon3);
        outStockTextView = (TextView) findViewById(R.id.outStock);
        icon4ImageView = (ImageView) findViewById(R.id.icon4);
        instockTextView = (TextView) findViewById(R.id.instock);
        icon_rightImageView = (ImageView) findViewById(R.id.icon_right);
        countTextView = (TextView) findViewById(R.id.count);
        listListView = (ListView) findViewById(R.id.list);
        setView();

    }

    private StockAllocateDetailPresenter stockAllocateDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTitle("库存调拨单");
        stockAllocateDetailPresenter.setText(R.id.right, "");
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_stock_allocate_detail;
    }

    @Override
    protected void setPresenter() {
        stockAllocateDetailPresenter = (StockAllocateDetailPresenter) (presenter =
                new StockAllocateDetailPresenter(StockAllocateDetailActivity.this
                ));
    }
}