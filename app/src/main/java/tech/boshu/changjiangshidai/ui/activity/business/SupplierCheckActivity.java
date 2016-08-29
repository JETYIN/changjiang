package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.SupplierAdapter;
import tech.boshu.changjiangshidai.bean.Supplier;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.utils.UIHelper;

/**
 * 客户对账 P7-1客户对账
 */
public class SupplierCheckActivity extends BaseActivity {

    private EditText et_Search;
    private TextView tv_AllClient;
    private TextView tv_ArrearsClient;
    private TextView tv_ArrearsAmount;
    private ListView lv_Supplier;
    private SupplierAdapter adapter;
    private List<Supplier> suppliers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.setText(R.id.right,"筛选");
        initTitle("供应商对账");
        presenter.setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showSupplierFilterActivity(SupplierCheckActivity.this);
            }
        });
        findViews();
        initData();
    }

    private void initData() {
        for(int i =0;i<5;i++){
            suppliers.add(new Supplier());
        }
        adapter = new SupplierAdapter(this,suppliers);
        lv_Supplier.setAdapter(adapter);
    }

    private void findViews() {
        et_Search = (EditText) findViewById(R.id.et_search);
        tv_AllClient = (TextView) findViewById(R.id.tv_all_client);
        tv_ArrearsClient = (TextView) findViewById(R.id.tv_arrears_client);
        tv_ArrearsAmount = (TextView) findViewById(R.id.tv_arrears_amount);
        lv_Supplier = (ListView) findViewById(R.id.lv_supplier);

        lv_Supplier.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //UIHelper.showClientArrearsActivity(ClientListActivity.this);
            }
        });

    }


    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_supplier_check;
    }
}

