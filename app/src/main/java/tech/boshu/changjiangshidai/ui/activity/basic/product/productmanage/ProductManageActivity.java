package tech.boshu.changjiangshidai.ui.activity.basic.product.productmanage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ProductManageAdapter;
import tech.boshu.changjiangshidai.bean.mode.Product;
import tech.boshu.changjiangshidai.libs.activity.SwipeRefreshBaseActivity;
import tech.boshu.changjiangshidai.libs.bean.Pagination;
import tech.boshu.changjiangshidai.ui.activity.basic.product.AddNewProductActivity;
import tech.boshu.changjiangshidai.ui.activity.basic.product.ProductSearchActivity;

/**
 * Created by Stone on 2016/1/6.
 * Project:changjiangshidai-android
 * Company:Pactera
 * Email:chenxi304102067@gmail.com
 */
public class ProductManageActivity extends SwipeRefreshBaseActivity implements IProductHistoryView,
        AdapterView.OnItemClickListener {
    private static final int REQUST_ADD_OR_EDIT_WAREHOURSE = 100;

    private ImageButton ibAdd;
    private ImageButton ibSearch;
    private ProductManageAdapter adapter;
    private List<Product> datas = new ArrayList<>();
    private PurchaseHistoryPresenter purchaseHistoryPresenter;
    private RadioGroup filter_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ibAdd = (ImageButton) findViewById(R.id.add);
        ibSearch = (ImageButton) findViewById(R.id.search);
        filter_group = (RadioGroup) findViewById(R.id.purchase_hostroy_group);
        listView.setAdapter(adapter);
        ibAdd.setOnClickListener(this);
        ibSearch.setOnClickListener(this);
        listView.setOnItemClickListener(this);
        filter_group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.filter_default:
                        purchaseHistoryPresenter.filterByDefault();
                        break;
                    case R.id.filter_count:
                        purchaseHistoryPresenter.filterByCount();
                        break;
                    case R.id.filter_draft:
                        purchaseHistoryPresenter.filterByDraft();
                        break;
                    case R.id.filter_cancel:
                        purchaseHistoryPresenter.filterByCancel();
                        break;
                }
                isRefresh = true;
                purchaseHistoryPresenter.getDatas();
            }
        });

        purchaseHistoryPresenter.setText(R.id.right, "");
        //第一个按钮为选中状态
        ((RadioButton) filter_group.getChildAt(0)).setChecked(true);
    }

    @Override
    protected void setLayoutResId() {
        this.layoutResId = R.layout.activity_product_manage;
    }

    @Override
    protected void setPresenter() {
        purchaseHistoryPresenter = (PurchaseHistoryPresenter) (presenter =
                new PurchaseHistoryPresenter(
                        ProductManageActivity.this));
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.add) {
            Intent intent = new Intent(mContext, AddNewProductActivity.class);
            intent.putExtra("type", AddNewProductActivity.ADD);
            startActivityForResult(intent, REQUST_ADD_OR_EDIT_WAREHOURSE);
        } else if (id == R.id.search) {
            startActivity(new Intent(mContext, ProductSearchActivity.class));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(mContext, AddNewProductActivity.class);
        intent.putExtra("type", AddNewProductActivity.EDIT);
        intent.putExtra("product", new Product().toString());
        startActivityForResult(intent, REQUST_ADD_OR_EDIT_WAREHOURSE);
    }

    @Override
    public void bindDatas(List<ResponseProductManage.DataEntity.PdListEntity> datas) {
        swipeLayout.setRefreshing(false);
        if (isRefresh) {
            initPageInfo();
            this.datas.clear();
        }
        for (ResponseProductManage.DataEntity.PdListEntity pd : datas) {
            Product product = new Product();
            product.name = pd.name;
            product.retailPrice = pd.retailPrice;
            product.picture = pd.picture;
            product.goodsNo = pd.goodsNo;
            this.datas.add(product);
        }
        if (adapter == null) {
            adapter = new ProductManageAdapter(mContext, this.datas);
            listView.setAdapter(adapter);
        } else {
            adapter.setData(this.datas);
        }
    }

    @Override
    public void showNoDatas() {
        swipeLayout.setRefreshing(false);
        this.datas.clear();
        adapter.setData(this.datas);
    }

    @Override
    public Pagination getPagination() {
        return pagination;
    }

    @Override
    public void setPagination(Pagination param) {
        this.pagination = param;
    }

    @Override
    public void stopRefresh() {
        swipeLayout.setRefreshing(false);
    }
}
