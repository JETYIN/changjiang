package tech.boshu.changjiangshidai.ui.activity.stockinfo.productstock;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)ProductStockActivity.java
 */


public class ProductStockActivity extends BaseActivity implements
        IProductStockView,
        CompoundButton.OnCheckedChangeListener {

    private TextView tvCheck;
    private ToggleButton nostockToggleButton;
    private ToggleButton nonestockToggleButton;
    private ToggleButton stockMergeToggleButton;
    private TextView nameTextView;
    private TextView wholesalseTextView;
    private TextView retailTextView;
    private TextView retailTitleTextView;
    private LinearLayout pricesLinearLayout;
    private TextView brandTextView;
    private TextView categoryTextView;
    private TextView totalStockTextView;
    private TextView check_flowTextView;
    private ImageView iconTipImageView;

    private ProductStockPresenter productStockPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_product_stock;
    }

    @Override
    protected void setPresenter() {
        productStockPresenter = new ProductStockPresenter(this);
        presenter = productStockPresenter;
    }

    public void initView() {
        productStockPresenter.setProductId(getIntent().getIntExtra("productId", -1));

        tvCheck = (TextView) findViewById(R.id.right);
        nostockToggleButton = (ToggleButton) findViewById(R.id.nostock);
        nonestockToggleButton = (ToggleButton) findViewById(R.id.nonestock);
        stockMergeToggleButton = (ToggleButton) findViewById(R.id.stockMerge);
        nameTextView = (TextView) findViewById(R.id.name);
        wholesalseTextView = (TextView) findViewById(R.id.wholesalse);
        retailTextView = (TextView) findViewById(R.id.retail);
        retailTitleTextView = (TextView) findViewById(R.id.retailTitle);
        pricesLinearLayout = (LinearLayout) findViewById(R.id.prices);
        brandTextView = (TextView) findViewById(R.id.brand);
        categoryTextView = (TextView) findViewById(R.id.category);
        totalStockTextView = (TextView) findViewById(R.id.totalStock);
        check_flowTextView = (TextView) findViewById(R.id.check_flow);
        iconTipImageView = (ImageView) findViewById(R.id.iconTip);
        initTitle("商品库存");
        tvCheck.setText("盘点");
        tvCheck.setOnClickListener(this);
        nonestockToggleButton.setOnCheckedChangeListener(this);

        productStockPresenter.setProductStock();
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.right) {
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
        int id = compoundButton.getId();
        if (id == R.id.nonestock) {
            productStockPresenter.setZeroNumStatus(checked ? 1 : 0);
            productStockPresenter.setProductStock();
        }
    }

    @Override
    public void setProductStockInfo(String name,
                                    double purchasePrice,
                                    double wholesalePrice,
                                    double retailPrice,
                                    String brand,
                                    String catalog,
                                    int mergerNum) {
        nameTextView.setText(name);
        wholesalseTextView.setText("采购价：￥" + purchasePrice);
        retailTextView.setText("批发价：￥" + wholesalePrice);
        retailTitleTextView.setText("零售价：￥" + retailPrice);
        brandTextView.setText("品牌：" + brand);
        categoryTextView.setText("分类：" + catalog);
        totalStockTextView.setText("总库存：" + mergerNum);
    }

}