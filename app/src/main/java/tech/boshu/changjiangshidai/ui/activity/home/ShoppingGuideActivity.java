package tech.boshu.changjiangshidai.ui.activity.home;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.android.volley.toolbox.NetworkImageView;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ProductInventoryAdapter;
import tech.boshu.changjiangshidai.bean.mode.ProductStore;
import tech.boshu.changjiangshidai.bean.mode.ResponseAddProuct;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.net.HttpLoad;
import tech.boshu.changjiangshidai.libs.utils.Utils;
import tech.boshu.changjiangshidai.libs.view.AutoHeightListView;
import tech.boshu.changjiangshidai.ui.activity.basic.product.AddNewProductActivity;
import tech.boshu.changjiangshidai.ui.activity.home.interactor.ShoppingGuideInteractorImpl;
import tech.boshu.changjiangshidai.ui.activity.home.presenter.ShoppingGuidePresenter;
import tech.boshu.changjiangshidai.ui.activity.home.view.IShoppingGuideView;
import tech.boshu.changjiangshidai.ui.activity.sale.AddProductActivity;

public class ShoppingGuideActivity extends BaseActivity {


    private TextView nameTextView;
    private RelativeLayout pricesRelativeLayout;
    private TextView wholesalseTextView;
    private TextView retailTextView;
    private TextView stockTextView;
    private TextView wholesalseTitleTextView;
    private TextView retailTitleTextView;
    private TextView stockTitleTextView;
    private TextView areaTextView;
    private TextView standerTextView;
    private TextView brandTextView;
    private TextView categoryTextView;
    private ImageView iconTipImageView;
    private ImageView iconTip1ImageView;
    private ToggleButton stockMergeToggleButton;
    private ResponseAddProuct responseAddProuct = new ResponseAddProuct();
    private Gson gson = new Gson();
    private int position;
    private LinearLayout lytProductIcon;
    private AutoHeightListView ahStoreList;
    private LinearLayout lytProductPropertyValue;
    private LinearLayout lytProductProperty;
    private LinearLayout lytListviewTopline;
    private TextView tvProductName;
    private TextView tvProductSize;
    private TextView tvProductStock;
    private ProductInventoryAdapter adapter;
    private List<ProductStore> datas = new ArrayList<>();
    private String data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent intent = getIntent();
        responseAddProuct = gson.fromJson(intent.getStringExtra("data"), ResponseAddProuct.class);
        position = intent.getIntExtra("position", -1);

        presenter.setText(R.id.right, "编辑");
        initTitle("商品导购");
        presenter.setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ShoppingGuideActivity.this, AddNewProductActivity.class);
                data = gson.toJson(responseAddProuct.data.pdList.get(position));
                intent1.putExtra("type", 1);
                intent1.putExtra("product", data);
                startActivity(intent1);
            }
        });
        setView();
        setData(position, responseAddProuct);
    }


    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_shopping_guide;
    }

    private void setView() {
        nameTextView = (TextView) findViewById(R.id.name);
        pricesRelativeLayout = (RelativeLayout) findViewById(R.id.prices);
        wholesalseTextView = (TextView) findViewById(R.id.wholesalse);
        retailTextView = (TextView) findViewById(R.id.retail);
        stockTextView = (TextView) findViewById(R.id.stock);
        wholesalseTitleTextView = (TextView) findViewById(R.id.wholesalseTitle);
        retailTitleTextView = (TextView) findViewById(R.id.retailTitle);
        stockTitleTextView = (TextView) findViewById(R.id.stockTitle);
        areaTextView = (TextView) findViewById(R.id.area);
        standerTextView = (TextView) findViewById(R.id.stander);
        brandTextView = (TextView) findViewById(R.id.brand);
        categoryTextView = (TextView) findViewById(R.id.category);
        iconTipImageView = (ImageView) findViewById(R.id.iconTip);
        iconTip1ImageView = (ImageView) findViewById(R.id.iconTip1);
        stockMergeToggleButton = (ToggleButton) findViewById(R.id.stockMerge);
        lytProductIcon = (LinearLayout) findViewById(R.id.product_icon);
        ahStoreList = (AutoHeightListView) findViewById(R.id.store_list);
        lytProductProperty = (LinearLayout) findViewById(R.id.product_property);
        lytProductPropertyValue = (LinearLayout) findViewById(R.id.product_property_value);
        tvProductName = (TextView) findViewById(R.id.product_name);
        tvProductSize = (TextView) findViewById(R.id.product_size);
        tvProductStock = (TextView) findViewById(R.id.product_stock);
        lytListviewTopline = (LinearLayout) findViewById(R.id.listview_topline);
        ahStoreList.setVisibility(View.VISIBLE);
        lytListviewTopline.setVisibility(View.VISIBLE);
        lytProductProperty.setVisibility(View.GONE);
        lytProductPropertyValue.setVisibility(View.GONE);

        adapter = new ProductInventoryAdapter(this, datas);
        ahStoreList.setAdapter(adapter);

        stockMergeToggleButton.setOnCheckedChangeListener(new CompoundButton
                .OnCheckedChangeListener() {


            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    ahStoreList.setVisibility(View.GONE);
                    lytListviewTopline.setVisibility(View.GONE);
                    lytProductProperty.setVisibility(View.VISIBLE);
                    lytProductPropertyValue.setVisibility(View.VISIBLE);
                } else {
                    ahStoreList.setVisibility(View.VISIBLE);
                    lytListviewTopline.setVisibility(View.VISIBLE);
                    lytProductProperty.setVisibility(View.GONE);
                    lytProductPropertyValue.setVisibility(View.GONE);
                }
            }
        });
    }

    private void setData(int position, ResponseAddProuct responseAddProuct) {
        nameTextView.setText(responseAddProuct.data.pdList.get(position).name);
        stockTextView.setText(String.valueOf(responseAddProuct.data.pdList.get(position)
                .mergerNum));
        retailTextView.setText("￥" + String.valueOf(responseAddProuct.data.pdList.get(position)
                .retailPrice));
        wholesalseTextView.setText("￥" + String.valueOf(responseAddProuct.data.pdList.get
                (position).wholesalePrice));
        areaTextView.setText(responseAddProuct.data.pdList.get(position).address);
        standerTextView.setText(responseAddProuct.data.pdList.get(position).spec);
        brandTextView.setText(responseAddProuct.data.pdList.get(position).brand);
        categoryTextView.setText(responseAddProuct.data.pdList.get(position).catalog);

        if (responseAddProuct.data.pdList.get(position).picList != null) {
            lytProductIcon.removeAllViews();
            for (int i = 0; i < responseAddProuct.data.pdList.get(position).picList.size(); i++) {
                View view = LayoutInflater.from(this).inflate(R.layout.layout_product_icon,
                        lytProductIcon, false);
                ImageView imageView = (ImageView) view.findViewById(R.id
                        .icon);
                imageView.getLayoutParams().width = Utils.getScreenWidth(mContext) / 3;
                imageView.getLayoutParams().height = Utils.getScreenWidth(mContext) / 3;
                HttpLoad.getImage(responseAddProuct.data.pdList.get(position).picList
                        .get(i).path, imageView);
                lytProductIcon.addView(view);

            }
        }
        tvProductName.setText(responseAddProuct.data.pdList.get(position).name);
        tvProductSize.setText(responseAddProuct.data.pdList.get(position).spec);
        tvProductStock.setText(String.valueOf(responseAddProuct.data.pdList.get(position)
                .mergerNum));
        datas = responseAddProuct.data.pdList.get(position).stroeList;
        adapter.setData(datas);

    }
}


