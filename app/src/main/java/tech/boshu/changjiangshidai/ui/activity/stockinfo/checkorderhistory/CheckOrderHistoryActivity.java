package tech.boshu.changjiangshidai.ui.activity.stockinfo.checkorderhistory;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.CheckOrderHistoryAdapter;
import tech.boshu.changjiangshidai.bean.result.CheckOrderHistory;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.addcheckorder.AddCheckOrderActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkdetail.CheckDetailActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.checkordersearch.CheckOrderSearchActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)CheckOrderHistoryActivity.java
 */


public class CheckOrderHistoryActivity extends BaseActivity implements
        ICheckOrderHistoryView,
        AdapterView.OnItemClickListener {
    public static int REQUEST_SEARCH = 0;
    public static int REQUEST_ADD = 1;
    public static int REQUEST_EDIT = 2;

    private ImageButton addImageButton;
    private ImageButton searchImageButton;
    private ListView listListView;
    private CheckOrderHistoryAdapter adapter;

    private CheckOrderHistoryPresenter checkOrderHistoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_check_order_history;
    }

    @Override
    protected void setPresenter() {
        checkOrderHistoryPresenter = new CheckOrderHistoryPresenter(this);
        presenter = checkOrderHistoryPresenter;
    }

    public void initView() {
        addImageButton = (ImageButton) findViewById(R.id.add);
        searchImageButton = (ImageButton) findViewById(R.id.search);
        listListView = (ListView) findViewById(R.id.list);
        adapter = new CheckOrderHistoryAdapter(mContext, null);
        listListView.setAdapter(adapter);
        addImageButton.setOnClickListener(this);
        searchImageButton.setOnClickListener(this);
        listListView.setOnItemClickListener(this);

        checkOrderHistoryPresenter.setHistoryList();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.add) {
            Intent intent = new Intent(mContext, AddCheckOrderActivity.class);
            startActivity(intent);
        } else if (id == R.id.search) {
            Intent intent = new Intent(mContext, CheckOrderSearchActivity.class);
            startActivityForResult(intent, REQUEST_SEARCH);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        checkOrderHistoryPresenter.clickHistory(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_SEARCH) {
                checkOrderHistoryPresenter.setSearchCondotion(
                        data.getIntExtra("storeId", -1),
                        data.getStringExtra("orderId"),
                        data.getStringExtra("startDate"),
                        data.getStringExtra("endDate"));
                checkOrderHistoryPresenter.setHistoryList();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setHistoryList(List<CheckOrderHistory> checkList) {
        adapter.setData(checkList);
    }

    @Override
    public void naviToCheckDetail(String orderId) {
        Intent intent = new Intent(mContext, CheckDetailActivity.class);
        intent.putExtra("orderId", orderId);
        startActivity(intent);
    }

    @Override
    public void naviToCheckEdit(String orderId) {
        Intent intent = new Intent(mContext, AddCheckOrderActivity.class);
        intent.putExtra("orderId", orderId);
        startActivity(intent);
    }

}