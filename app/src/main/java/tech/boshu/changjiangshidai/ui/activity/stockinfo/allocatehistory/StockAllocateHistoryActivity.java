package tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatehistory;

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
import tech.boshu.changjiangshidai.adapter.StockAllocateHistoryAdapter;
import tech.boshu.changjiangshidai.bean.StockAllocateHistoryItem;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addstockallocate.AddStockAllocateOrderActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatedetail.StockAllocateDetailActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.allocatesearch.StockAllocateSearchActivity;

/**
 * @(#)StockAllocateHistoryActivity.java
 *
 *
 * @author allipper
 * @version 1.00 2016/1/6
 */


public class StockAllocateHistoryActivity extends BaseActivity
		implements IStockAllocateHistoryView {

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

	private StockAllocateHistoryAdapter adapter;
	private List<StockAllocateHistoryItem> datas = new ArrayList<>();

	public void setView() {

		setText(R.id.orderId, "PD33333333");
		setText(R.id.maker, "徐莎");
		setText(R.id.outStock, "四楼仓库");
		setText(R.id.instock, "五楼仓库");
		setText(R.id.count, "4");

		for (int i = 0; i < 6; i++) {
			datas.add(new StockAllocateHistoryItem());
		}
		adapter = new StockAllocateHistoryAdapter(mContext, datas);
		listListView.setAdapter(adapter);
		listListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent intent = new Intent(mContext, StockAllocateDetailActivity.class);
				startActivity(intent);
			}
		});
		setOnclickListener(R.id.add, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stockAllocateHistoryPresenter.add();
			}
		});
		setOnclickListener(R.id.search, new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				stockAllocateHistoryPresenter.gotoSearch();
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

	}
	private StockAllocateHistoryPresenter stockAllocateHistoryPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		initView();
		initTitle("库存调拨历史");
		stockAllocateHistoryPresenter.setText(R.id.right, "");
	}

	@Override
	protected void setLayoutResId()
	{
		layoutResId = R.layout.activity_stock_allocate_history;
	}

	@Override
	protected void setPresenter()
	{
		stockAllocateHistoryPresenter = (StockAllocateHistoryPresenter) (presenter =
				new StockAllocateHistoryPresenter(StockAllocateHistoryActivity.this
						));
	}


	public void gotoSearch() {
		Intent intent = new Intent(mContext, StockAllocateSearchActivity.class);
		startActivity(intent);
	}

	public void add() {
		Intent intent = new Intent(mContext, AddStockAllocateOrderActivity.class);
		startActivity(intent);
	}
}