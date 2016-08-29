package tech.boshu.changjiangshidai.ui.activity.basic.warehouse;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.WarehourseProductAdapter;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;

/**
 * Created by zoulinlin on 16/1/6.
 */
public class WarehouseInitActivity extends BaseActivity {

    TextView subTotalTextView;
    Button saveButton;
    ListView listView;
    View headerView;
    WarehourseProductAdapter productAdapter;
    TextView chooseWarehourseTextView;
    TextView chooseProductTextView;
    TextView chooseAreaTextView;
    TextView chooseSizeTextView;
    TextView curNumTextView;
    EditText initNumTextView;

    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_warehouse_init;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right, "初始化结束");
        initTitle("仓库初始化");
        setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.back(view);
            }
        });
        findViews();
    }

    private void findViews() {
        subTotalTextView = (TextView) findViewById(R.id.sub_total);
        saveButton = (Button) findViewById(R.id.save_button);
        listView = (ListView) findViewById(R.id.list_view);
        headerView = LayoutInflater.from(this).inflate(R.layout.layout_warehourse_header_view,
                null);
        listView.addHeaderView(headerView);
        productAdapter = new WarehourseProductAdapter(this, null);
        listView.setAdapter(productAdapter);

        chooseWarehourseTextView = (TextView) headerView.findViewById(R.id
                .selected_warehourse_name);
        chooseProductTextView = (TextView) headerView.findViewById(R.id.selected_product_name);
        chooseSizeTextView = (TextView) headerView.findViewById(R.id.selected_size_name);
        chooseAreaTextView = (TextView) headerView.findViewById(R.id.selected_area_name);
        initNumTextView = (EditText) headerView.findViewById(R.id.init_num);
        curNumTextView = (TextView) headerView.findViewById(R.id.cur_num);

        chooseProductTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.selected_product_name) {
            startActivity(new Intent(mContext, SeleteProductActivity.class));
        }
    }
}
