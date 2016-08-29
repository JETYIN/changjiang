package tech.boshu.changjiangshidai.ui.activity.stockinfo.addstockallocate;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.view.DrawableLeftCenterButton;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)AddStockAllocateOrderActivity.java
 */


public class AddStockAllocateOrderActivity extends BaseActivity implements IAddStockAllocateOrderView {

    private RelativeLayout bottomRelativeLayout;
    private TextView total_priceTextView;
    private TextView total_price_valueTextView;
    private Button draftButton;
    private Button saleButton;
    private TextView outstockTextView;
    private TextView outstockvalueTextView;
    private TextView instockTextView;
    private TextView instockvalueTextView;
    private TextView productTextView;
    private TextView productvalueTextView;
    private TextView pay_wayTextView;
    private EditText pay_way_valueEditText;
    private TextView afterCountTextView;
    private TextView beforeCountTextView;
    private EditText countEditText;
    private DrawableLeftCenterButton addToAllocateOrderDrawableLeftCenterButton;
    private ImageView icon_rightImageView;
    private TextView editTextView;
    private ListView listListView;
    private EditText remarkEditText;


    public void setView() {

        setText(R.id.total_price_value, "￥34000");
        setText(R.id.afterCount, "调出前数量：21");
        setText(R.id.beforeCount, "调入前数量：15");
    }

    public void initView() {
        bottomRelativeLayout = (RelativeLayout) findViewById(R.id.bottom);
        total_priceTextView = (TextView) findViewById(R.id.total_price);
        total_price_valueTextView = (TextView) findViewById(R.id.total_price_value);
        draftButton = (Button) findViewById(R.id.draft);
        saleButton = (Button) findViewById(R.id.sale);
        outstockTextView = (TextView) findViewById(R.id.outstock);
        outstockvalueTextView = (TextView) findViewById(R.id.outstockvalue);
        instockTextView = (TextView) findViewById(R.id.instock);
        instockvalueTextView = (TextView) findViewById(R.id.instockvalue);
        productTextView = (TextView) findViewById(R.id.product);
        productvalueTextView = (TextView) findViewById(R.id.productvalue);
        pay_wayTextView = (TextView) findViewById(R.id.pay_way);
        pay_way_valueEditText = (EditText) findViewById(R.id.pay_way_value);
        afterCountTextView = (TextView) findViewById(R.id.afterCount);
        beforeCountTextView = (TextView) findViewById(R.id.beforeCount);
        countEditText = (EditText) findViewById(R.id.count);
        addToAllocateOrderDrawableLeftCenterButton = (DrawableLeftCenterButton) findViewById(R.id.addToAllocateOrder);
        icon_rightImageView = (ImageView) findViewById(R.id.icon_right);
        editTextView = (TextView) findViewById(R.id.edit);
        listListView = (ListView) findViewById(R.id.list);
        remarkEditText = (EditText) findViewById(R.id.remark);

    }

    private AddStockAllocateOrderPresenter addStockAllocateOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTitle("新增库存调拨单");
        addStockAllocateOrderPresenter.setText(R.id.right, "");
        setView();
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_add_stock_allocate_order;
    }

    @Override
    protected void setPresenter() {
        addStockAllocateOrderPresenter = (AddStockAllocateOrderPresenter) (presenter =
                new AddStockAllocateOrderPresenter(AddStockAllocateOrderActivity.this));
    }
}