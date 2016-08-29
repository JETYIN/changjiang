package tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.SearchProductAdapter;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock.ProductStockActivity;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.search.StockSearchActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)StockSearchResultActivity.java
 */


public class StockSearchResultActivity extends BaseActivity implements
        IStockSearchResultView,
        AdapterView.OnItemClickListener {

    private static final int REQUEST_CODE_SEARCH = 1;
    private static final int REQUEST_CODE_SCAN = 2;

    private RelativeLayout title_topRelativeLayout;
    private ImageButton scanImageButton;
    private ImageButton ivSearch;
    private TextView countTextView;
    private TextView totalCountTextView;
    private TextView totalPriceTextView;
    private TextView totalWarnTextView;
    private TextView sort_defaultTextView;
    private TextView sort_nameTextView;
    private TextView sort_codeTextView;
    private TextView sort_countTextView;
    private ListView listListView;
    private SearchProductAdapter adapter;

    private StockSearchResultPresenterImpl presenterImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_stock_search_result;
    }

    @Override
    protected void setPresenter() {
        presenterImpl = new StockSearchResultPresenterImpl(this);
        presenter = presenterImpl;
    }

    public void initView() {
        title_topRelativeLayout = (RelativeLayout) findViewById(R.id.title_top);
        scanImageButton = (ImageButton) findViewById(R.id.scan);
        ivSearch = (ImageButton) findViewById(R.id.search);
        countTextView = (TextView) findViewById(R.id.count);
        totalCountTextView = (TextView) findViewById(R.id.total_count);
        totalPriceTextView = (TextView) findViewById(R.id.total_price);
        totalWarnTextView = (TextView) findViewById(R.id.total_warn);
        sort_defaultTextView = (TextView) findViewById(R.id.sort_default);
        sort_nameTextView = (TextView) findViewById(R.id.sort_name);
        sort_codeTextView = (TextView) findViewById(R.id.sort_code);
        sort_countTextView = (TextView) findViewById(R.id.sort_count);
        listListView = (ListView) findViewById(R.id.list);
        initTitle("库存查询");
        adapter = new SearchProductAdapter(mContext, null);
        listListView.setAdapter(adapter);
        listListView.setOnItemClickListener(this);
        ivSearch.setOnClickListener(this);

        presenterImpl.setStoreInfor(-1, -1, -1, -1, -1);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.search) {
            Intent intent = new Intent(mContext, StockSearchActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SEARCH);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        presenterImpl.naviToProductStock(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_SEARCH) {
                presenterImpl.setStoreInfor(
                        data.getIntExtra("storeId", -1),
                        data.getIntExtra("catalogId", -1),
                        data.getIntExtra("brandId", -1),
                        data.getIntExtra("minNum", -1),
                        data.getIntExtra("maxNum", -1));
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void setStoreStatus(String storeNum,
                               String storeAllNum,
                               String storeAllMoney,
                               String storeAllWarn) {
        countTextView.setText(storeNum);
        totalCountTextView.setText(storeAllNum);
        totalPriceTextView.setText(storeAllMoney);
        totalWarnTextView.setText(storeAllWarn);
    }

    @Override
    public void setProductList(List<ModelStockProduct> productList) {
        adapter.setData(productList);
    }

    @Override
    public void naviToProductStock(int productId) {
        Intent intent = new Intent(mContext, ProductStockActivity.class);
        intent.putExtra("productId", productId);
        startActivity(intent);
    }

}