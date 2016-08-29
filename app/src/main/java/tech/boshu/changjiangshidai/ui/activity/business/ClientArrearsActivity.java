package tech.boshu.changjiangshidai.ui.activity.business;


import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import tech.boshu.changjiangshidai.R;
import tech.boshu.changjiangshidai.adapter.ClientArrearsBillsAdapter;
import tech.boshu.changjiangshidai.bean.ClientArrearsBill;
import tech.boshu.changjiangshidai.libs.activity.BaseActivity;
import tech.boshu.changjiangshidai.utils.UIHelper;

/**
 * 欠款客户 P7-1-2客户
 */
public class ClientArrearsActivity extends BaseActivity {

    private TextView tv_All;
    private TextView tv_Arrears;
    private ListView lv_ClientBill;
    private ClientArrearsBillsAdapter adapter;
    private List<ClientArrearsBill> clientArrearsBills = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initTitle("客户名称");
        initTitle("筛选");
        setOnclickListener(R.id.right, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UIHelper.showBillFilterActivity(ClientArrearsActivity.this);
            }
        });
        findViews();
        initData();
    }

    private void initData() {
        tv_All.setSelected(true);
        for(int i =0;i<5;i++){
            clientArrearsBills.add(new ClientArrearsBill());
        }
        adapter = new ClientArrearsBillsAdapter(this, clientArrearsBills);
        lv_ClientBill.setAdapter(adapter);
    }

    private void findViews() {
        tv_All = (TextView) findViewById(R.id.tv_all);
        tv_Arrears = (TextView) findViewById(R.id.tv_arrears);
        lv_ClientBill = (ListView) findViewById(R.id.lv_arrears_bill);

        View header = getLayoutInflater().inflate(R.layout.listview_header_client_arrares,null);
        lv_ClientBill.addHeaderView(header);

        findViewById(R.id.rl_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_All.setSelected(true);
                tv_Arrears.setSelected(false);
            }
        });
        findViewById(R.id.rl_arrears).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_All.setSelected(false);
                tv_Arrears.setSelected(true);
            }
        });
    }


    @Override
    protected void setLayoutResId() {
        layoutResId = R.layout.activity_client_arrears;
    }
}

