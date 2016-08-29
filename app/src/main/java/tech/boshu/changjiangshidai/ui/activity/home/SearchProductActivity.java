package tech.boshu.changjiangshidai.ui.activity.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.SearchProductAdapter;
import tech.boshu.changjiangshidai.bean.api.SalesRequest;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.bean.mode.ResponseAddProuct;
import tech.boshu.changjiangshidai.libs.activity.SwipeRefreshBaseActivity;
import tech.boshu.changjiangshidai.libs.net.ResponseCallback;
import tech.boshu.changjiangshidai.libs.net.sourceforge.simcpux.ErrorBase;
import tech.boshu.changjiangshidai.libs.utils.ToastUtils;
import tech.boshu.changjiangshidai.ui.activity.stockinfo.searchresult.ModelStockProduct;

public class SearchProductActivity extends SwipeRefreshBaseActivity {
    private static final String TAG = SearchProductActivity.class.getSimpleName();

    private LinearLayout title_topLinearLayout;
    private ImageView backImageView;
    private FrameLayout searchFrameLayout;
    private TextView titleTextView;
    private ListView listListView;
    private SearchProductAdapter adapter;
    private List<ModelStockProduct> datas = new ArrayList<>();
    private String companyId = "2222";
    private String strId;
    private String catalogId;
    private String brandId;
    private String status;
    private Gson gson = new Gson();
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right, "保存");
        setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.back(view);
            }
        });
        findViews();
        getData(false);
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_search_product;
    }

    private void getData(boolean isShowDialog) {

        SalesRequest.queruProductList(companyId, strId, catalogId, brandId, status, new
                ResponseCallback<ResponseAddProuct>() {

                    @Override
                    public void onRequestSuccess(ResponseAddProuct result) {
                        if (result != null) {
                            for (int i = 0; i < result.data.pdList.size(); i++) {
                                ModelStockProduct modelStockProduct = new ModelStockProduct();
                                modelStockProduct.goodsNo = result.data.pdList.get(i).goodsNo;
                                modelStockProduct.pdName = result.data.pdList.get(i).name;
                                modelStockProduct.mergerNum = result.data.pdList.get(i).mergerNum;
                                modelStockProduct.picture = result.data.pdList.get(i).picture;
                                datas.add(modelStockProduct);
                            }

                            adapter.setData(datas);
                            data = gson.toJson(result);
                        }
                        ToastUtils.show(mContext, "成功");
                    }

                    @Override
                    public void onReuquestFailed(ErrorBase error) {
                        ToastUtils.show(mContext, error.message);
                    }
                });
    }

    private void findViews() {
        title_topLinearLayout = (LinearLayout) findViewById(R.id.title_top);
        backImageView = (ImageView) findViewById(R.id.back);
        searchFrameLayout = (FrameLayout) findViewById(R.id.search);
        titleTextView = (TextView) findViewById(R.id.title);
        listListView = (ListView) findViewById(R.id.list);


        adapter = new SearchProductAdapter(mContext, datas);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(mContext, ShoppingGuideActivity.class);
                intent.putExtra("data", data);
                intent.putExtra("position", i);
                startActivity(intent);
            }
        });
    }


}

