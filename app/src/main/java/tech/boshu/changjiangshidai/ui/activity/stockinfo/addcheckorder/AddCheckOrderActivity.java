package tech.boshu.changjiangshidai.ui.activity.stockinfo.addcheckorder;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.AddCheckOrderAdapter;
import tech.boshu.changjiangshidai.bean.CheckOrderDetail;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.libs.view.DrawableLeftCenterButton;

/**
 * @author allipper
 * @version 1.00 2016/1/6
 * @(#)AddCheckOrderActivity.java
 */


public class AddCheckOrderActivity extends BaseActivity implements IAddCheckOrderView {

    private RelativeLayout bottomRelativeLayout;
    private TextView total_priceTextView;
    private TextView total_price_valueTextView;
    private TextView stock_nameTextView;
    private TextView stockvalueTextView;
    private TextView productTextView;
    private TextView productvalueTextView;
    private TextView scancodeTextView;
    private EditText scancodevalueEditText;
    private TextView check_countTextView;
    private DrawableLeftCenterButton add_check_orderDrawableLeftCenterButton;
    private ImageView icon_rightImageView;
    private ListView listListView;
    private EditText remarkEditText;

    private AddCheckOrderAdapter adapter;
    private List<CheckOrderDetail> datas = new ArrayList<>();
    private AddCheckOrderPresenter addCheckOrderPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initTitle("添加盘点单");
        addCheckOrderPresenter.setText(R.id.right, "");
    }

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_add_check_order;
    }

    @Override
    protected void setPresenter() {
        addCheckOrderPresenter = (AddCheckOrderPresenter) (presenter = new AddCheckOrderPresenter
                (AddCheckOrderActivity.this));
    }


    public void setView() {

        setText(R.id.check_count, "盘点数量：15");
    }

    public void initView() {
        bottomRelativeLayout = (RelativeLayout) findViewById(R.id.bottom);
        total_priceTextView = (TextView) findViewById(R.id.total_price);
        total_price_valueTextView = (TextView) findViewById(R.id.total_price_value);
        stock_nameTextView = (TextView) findViewById(R.id.stock_name);
        stockvalueTextView = (TextView) findViewById(R.id.stockvalue);
        productTextView = (TextView) findViewById(R.id.product);
        productvalueTextView = (TextView) findViewById(R.id.productvalue);
        scancodeTextView = (TextView) findViewById(R.id.scancode);
        scancodevalueEditText = (EditText) findViewById(R.id.scancodevalue);
        check_countTextView = (TextView) findViewById(R.id.check_count);
        add_check_orderDrawableLeftCenterButton = (DrawableLeftCenterButton) findViewById(R.id
                .add_check_order);
        icon_rightImageView = (ImageView) findViewById(R.id.icon_right);
        listListView = (ListView) findViewById(R.id.list);
        remarkEditText = (EditText) findViewById(R.id.remark);
        for (int i = 0; i < 2; i++) {
            datas.add(new CheckOrderDetail());
        }
        adapter = new AddCheckOrderAdapter(mContext, datas);
        listListView.setAdapter(adapter);
        listListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(context, ProductStockActivity.class);
//                context.startActivity(intent);
            }
        });
        setView();
    }
}